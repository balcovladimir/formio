/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.formio.ajax.error;

import java.util.Locale;

import net.formio.RequestParams;
import net.formio.ajax.AjaxResponse;
import net.formio.format.Location;
import net.formio.render.FormRenderer;

/**
 * {@link AjaxErrorHandler} that shows an alert using AJAX response.
 * @author Radek Beran
 */
public class AjaxAlertErrorHandler<T> implements AjaxErrorHandler<T> {

	/**
	 * Handles error in processing an AJAX request by generating AJAX response that shows "AJAX error" alert.
	 * @param requestParams
	 * @param cause exception thrown during processing an AJAX request
	 */
	@Override
	public AjaxResponse<T> errorResponse(RequestParams requestParams, Throwable cause) {
		return new AjaxResponse<T>(new FormRenderer(Location.getInstance(Locale.ENGLISH)).ajaxResponse()
			.status("ERROR")
			.script("alert(\"AJAX Error\")")
			.asString());
	}

}
