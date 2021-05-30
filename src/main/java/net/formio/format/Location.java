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
package net.formio.format;

import java.util.Locale;
import java.util.TimeZone;

/**
 * Represents a specific geographical or cultural region including a time zone offset.
 * 
 * @author Radek Beran
 */
public final class Location {
	private final Locale locale;
	
	private final TimeZone timeZone;
	
	public static final Location DEFAULT = new Location(Locale.getDefault(), TimeZone.getDefault());
	
	public static final Location CZECH = getInstance(new Locale("cs"));
	
	public static final Location ENGLISH = getInstance(Locale.ENGLISH);

    public static final Location FRENCH = getInstance(Locale.FRENCH);

    public static final Location GERMAN = getInstance(Locale.GERMAN);

    public static final Location ITALIAN = getInstance(Locale.ITALIAN);

    public static final Location JAPANESE = getInstance(Locale.JAPANESE);

    public static final Location KOREAN = getInstance(Locale.KOREAN);

    public static final Location CHINESE = getInstance(Locale.CHINESE);

    public static final Location SIMPLIFIED_CHINESE = getInstance(Locale.SIMPLIFIED_CHINESE);
    
    public static final Location SLOVAK = getInstance(new Locale("sk"));

    public static final Location TRADITIONAL_CHINESE = getInstance(Locale.TRADITIONAL_CHINESE);
    
    public static final Location CZECHIA = getInstance(new Locale("cs", "CZ"));

    public static final Location FRANCE = getInstance(Locale.FRANCE);

    public static final Location GERMANY = getInstance(Locale.GERMANY);

    public static final Location ITALY = getInstance(Locale.ITALY);

    public static final Location JAPAN = getInstance(Locale.JAPAN);

    public static final Location KOREA = getInstance(Locale.KOREA);

    public static final Location CHINA = SIMPLIFIED_CHINESE;

    public static final Location PRC = SIMPLIFIED_CHINESE;
    
    public static final Location SLOVAKIA = getInstance(new Locale("sk", "SK"));

    public static final Location TAIWAN = TRADITIONAL_CHINESE;

    public static final Location UK = getInstance(Locale.UK);

    public static final Location US = getInstance(Locale.US);

    public static final Location CANADA = getInstance(Locale.CANADA);

    public static final Location CANADA_FRENCH = getInstance(Locale.CANADA_FRENCH);
	
    public static Location getDefault() {
		return DEFAULT;
	}

	public static Location getInstance(Locale locale, TimeZone timeZone) {
		return new Location(locale, timeZone);
	}
	
	public static Location getInstance(Locale locale) {
		return getInstance(locale, TimeZone.getDefault());
	}
	
	public static Location getInstance(TimeZone timeZone) {
		return getInstance(Locale.getDefault(), timeZone);
	}
	
	public static Location getInstance() {
		return getInstance(Locale.getDefault(), TimeZone.getDefault());
	}
	
	private Location(Locale locale, TimeZone timeZone) {
		this.locale = locale;
		this.timeZone = timeZone;
	}

	public Locale getLocale() {
		return locale;
	}

	public TimeZone getTimeZone() {
		return timeZone;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((locale == null) ? 0 : locale.hashCode());
		result = prime * result + ((timeZone == null) ? 0 : timeZone.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Location))
			return false;
		Location other = (Location) obj;
		if (locale == null) {
			if (other.locale != null)
				return false;
		} else if (!locale.equals(other.locale))
			return false;
		if (timeZone == null) {
			if (other.timeZone != null)
				return false;
		} else if (!timeZone.equals(other.timeZone))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Location [locale=" + locale + ", timeZone=" + timeZone + "]";
	}
	
}
