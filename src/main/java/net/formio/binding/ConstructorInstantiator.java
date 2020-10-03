/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.formio.binding;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import net.formio.upload.UploadedFile;
import net.formio.upload.UploadedFileWrapper;

/**
 * Instantiates a class of type T using given constructor.
 * @author Radek Beran
 */
public class ConstructorInstantiator extends AbstractInstantiator {
	
	public ConstructorInstantiator() {
	}
	
	@Override
	public <T> T instantiate(Class<T> objClass, ConstructionDescription cd, Object ... args) {
		if (cd.getArgTypes().length != args.length) {
			throw new IllegalStateException("Number of argument types " + Arrays.toString(cd.getArgTypes()) + 
				" is not equal to number of arguments " + args.length + 
				" for constructor " + ((DefaultConstructionDescription)cd).getConstructionMethod());
		}
		return BindingReflectionUtils.requireNewInstance(
			(Constructor<T>)((DefaultConstructionDescription)cd).getConstructionMethod(),
			prepareArgs(cd.getArgTypes(), args));
	}
	
	@Override
	public <T> ConstructionDescription getDescription(Class<T> objClass, ArgumentNameResolver argNameResolver) {
		DefaultConstructionDescription desc = null;
		int maxArgCnt = -1; // we will choose the constructor with the max. count of usable named arguments
		for (Constructor<?> c : objClass.getConstructors()) { // all public constructors
			Class<?>[] argTypes = c.getParameterTypes();
			if (argTypes.length == 0 && 0 > maxArgCnt) {
				maxArgCnt = 0;
				desc = new DefaultConstructionDescription(objClass, c, Collections.<String>emptyList());
			} else {
				// For each constructor parameter
				List<String> argNames = new ArrayList<String>();
				for (int i = 0; i < argTypes.length; i++) {
					String argName = argNameResolver.getArgumentName(c, i);
					if (argName == null) {
						// Constructor contains argument
						// that cannot be bound and used in construction
						argNames.clear();
						break;
					}
					argNames.add(argName);
				}
				if (argNames.size() > 0 && argNames.size() > maxArgCnt) {
					// more than 0 arguments
					maxArgCnt = argNames.size();
					desc = new DefaultConstructionDescription(objClass, c, argNames);
				}
			}
		}
		if (desc == null) {
			String msgBase = "No usable public constructor of " + objClass.getName() + " was found. Did you forget to specify custom type of instantiator?";
			if (UploadedFile.class.isAssignableFrom(objClass)) {
				throw new IllegalStateException(msgBase + " If you want to use " + UploadedFile.class.getName() + " in nested mapping as an complex object, use " + UploadedFileWrapper.class.getName() + " instead.");
			}
			throw new IllegalStateException("No usable public constructor of " + objClass.getName() + " was found. Did you forget to specify custom type of instantiator?");
		}
		return desc;
	}
}
