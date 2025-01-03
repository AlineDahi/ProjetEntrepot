// ============================================================================
//
// Copyright (c) 2006-2015, Talend SA
//
// Ce code source a été automatiquement généré par_Talend Open Studio for Data Integration
// / Soumis à la Licence Apache, Version 2.0 (la "Licence") ;
// votre utilisation de ce fichier doit respecter les termes de la Licence.
// Vous pouvez obtenir une copie de la Licence sur
// http://www.apache.org/licenses/LICENSE-2.0
// 
// Sauf lorsqu'explicitement prévu par la loi en vigueur ou accepté par écrit, le logiciel
// distribué sous la Licence est distribué "TEL QUEL",
// SANS GARANTIE OU CONDITION D'AUCUNE SORTE, expresse ou implicite.
// Consultez la Licence pour connaître la terminologie spécifique régissant les autorisations et
// les limites prévues par la Licence.

package projettalend1.completude_nocs_0_1;

import routines.Numeric;
import routines.DataOperation;
import routines.TalendDataGenerator;
import routines.TalendStringUtil;
import routines.TalendString;
import routines.StringHandling;
import routines.Relational;
import routines.TalendDate;
import routines.Mathematical;
import routines.system.*;
import routines.system.api.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.math.BigDecimal;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.util.Comparator;

@SuppressWarnings("unused")

/**
 * Job: completude_nocs Purpose: <br>
 * Description: <br>
 * 
 * @author user@talend.com
 * @version 8.0.1.20211109_1610
 * @status
 */
public class completude_nocs implements TalendJob {

	protected static void logIgnoredError(String message, Throwable cause) {
		System.err.println(message);
		if (cause != null) {
			cause.printStackTrace();
		}

	}

	public final Object obj = new Object();

	// for transmiting parameters purpose
	private Object valueObject = null;

	public Object getValueObject() {
		return this.valueObject;
	}

	public void setValueObject(Object valueObject) {
		this.valueObject = valueObject;
	}

	private final static String defaultCharset = java.nio.charset.Charset.defaultCharset().name();

	private final static String utf8Charset = "UTF-8";

	// contains type for every context property
	public class PropertiesWithType extends java.util.Properties {
		private static final long serialVersionUID = 1L;
		private java.util.Map<String, String> propertyTypes = new java.util.HashMap<>();

		public PropertiesWithType(java.util.Properties properties) {
			super(properties);
		}

		public PropertiesWithType() {
			super();
		}

		public void setContextType(String key, String type) {
			propertyTypes.put(key, type);
		}

		public String getContextType(String key) {
			return propertyTypes.get(key);
		}
	}

	// create and load default properties
	private java.util.Properties defaultProps = new java.util.Properties();

	// create application properties with default
	public class ContextProperties extends PropertiesWithType {

		private static final long serialVersionUID = 1L;

		public ContextProperties(java.util.Properties properties) {
			super(properties);
		}

		public ContextProperties() {
			super();
		}

		public void synchronizeContext() {

		}

		// if the stored or passed value is "<TALEND_NULL>" string, it mean null
		public String getStringValue(String key) {
			String origin_value = this.getProperty(key);
			if (NULL_VALUE_EXPRESSION_IN_COMMAND_STRING_FOR_CHILD_JOB_ONLY.equals(origin_value)) {
				return null;
			}
			return origin_value;
		}

	}

	protected ContextProperties context = new ContextProperties(); // will be instanciated by MS.

	public ContextProperties getContext() {
		return this.context;
	}

	private final String jobVersion = "0.1";
	private final String jobName = "completude_nocs";
	private final String projectName = "PROJETTALEND1";
	public Integer errorCode = null;
	private String currentComponent = "";

	private final java.util.Map<String, Object> globalMap = new java.util.HashMap<String, Object>();
	private final static java.util.Map<String, Object> junitGlobalMap = new java.util.HashMap<String, Object>();

	private final java.util.Map<String, Long> start_Hash = new java.util.HashMap<String, Long>();
	private final java.util.Map<String, Long> end_Hash = new java.util.HashMap<String, Long>();
	private final java.util.Map<String, Boolean> ok_Hash = new java.util.HashMap<String, Boolean>();
	public final java.util.List<String[]> globalBuffer = new java.util.ArrayList<String[]>();

	private RunStat runStat = new RunStat();

	// OSGi DataSource
	private final static String KEY_DB_DATASOURCES = "KEY_DB_DATASOURCES";

	private final static String KEY_DB_DATASOURCES_RAW = "KEY_DB_DATASOURCES_RAW";

	public void setDataSources(java.util.Map<String, javax.sql.DataSource> dataSources) {
		java.util.Map<String, routines.system.TalendDataSource> talendDataSources = new java.util.HashMap<String, routines.system.TalendDataSource>();
		for (java.util.Map.Entry<String, javax.sql.DataSource> dataSourceEntry : dataSources.entrySet()) {
			talendDataSources.put(dataSourceEntry.getKey(),
					new routines.system.TalendDataSource(dataSourceEntry.getValue()));
		}
		globalMap.put(KEY_DB_DATASOURCES, talendDataSources);
		globalMap.put(KEY_DB_DATASOURCES_RAW, new java.util.HashMap<String, javax.sql.DataSource>(dataSources));
	}

	public void setDataSourceReferences(List serviceReferences) throws Exception {

		java.util.Map<String, routines.system.TalendDataSource> talendDataSources = new java.util.HashMap<String, routines.system.TalendDataSource>();
		java.util.Map<String, javax.sql.DataSource> dataSources = new java.util.HashMap<String, javax.sql.DataSource>();

		for (java.util.Map.Entry<String, javax.sql.DataSource> entry : BundleUtils
				.getServices(serviceReferences, javax.sql.DataSource.class).entrySet()) {
			dataSources.put(entry.getKey(), entry.getValue());
			talendDataSources.put(entry.getKey(), new routines.system.TalendDataSource(entry.getValue()));
		}

		globalMap.put(KEY_DB_DATASOURCES, talendDataSources);
		globalMap.put(KEY_DB_DATASOURCES_RAW, new java.util.HashMap<String, javax.sql.DataSource>(dataSources));
	}

	private final java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
	private final java.io.PrintStream errorMessagePS = new java.io.PrintStream(new java.io.BufferedOutputStream(baos));

	public String getExceptionStackTrace() {
		if ("failure".equals(this.getStatus())) {
			errorMessagePS.flush();
			return baos.toString();
		}
		return null;
	}

	private Exception exception;

	public Exception getException() {
		if ("failure".equals(this.getStatus())) {
			return this.exception;
		}
		return null;
	}

	private class TalendException extends Exception {

		private static final long serialVersionUID = 1L;

		private java.util.Map<String, Object> globalMap = null;
		private Exception e = null;
		private String currentComponent = null;
		private String virtualComponentName = null;

		public void setVirtualComponentName(String virtualComponentName) {
			this.virtualComponentName = virtualComponentName;
		}

		private TalendException(Exception e, String errorComponent, final java.util.Map<String, Object> globalMap) {
			this.currentComponent = errorComponent;
			this.globalMap = globalMap;
			this.e = e;
		}

		public Exception getException() {
			return this.e;
		}

		public String getCurrentComponent() {
			return this.currentComponent;
		}

		public String getExceptionCauseMessage(Exception e) {
			Throwable cause = e;
			String message = null;
			int i = 10;
			while (null != cause && 0 < i--) {
				message = cause.getMessage();
				if (null == message) {
					cause = cause.getCause();
				} else {
					break;
				}
			}
			if (null == message) {
				message = e.getClass().getName();
			}
			return message;
		}

		@Override
		public void printStackTrace() {
			if (!(e instanceof TalendException || e instanceof TDieException)) {
				if (virtualComponentName != null && currentComponent.indexOf(virtualComponentName + "_") == 0) {
					globalMap.put(virtualComponentName + "_ERROR_MESSAGE", getExceptionCauseMessage(e));
				}
				globalMap.put(currentComponent + "_ERROR_MESSAGE", getExceptionCauseMessage(e));
				System.err.println("Exception in component " + currentComponent + " (" + jobName + ")");
			}
			if (!(e instanceof TDieException)) {
				if (e instanceof TalendException) {
					e.printStackTrace();
				} else {
					e.printStackTrace();
					e.printStackTrace(errorMessagePS);
					completude_nocs.this.exception = e;
				}
			}
			if (!(e instanceof TalendException)) {
				try {
					for (java.lang.reflect.Method m : this.getClass().getEnclosingClass().getMethods()) {
						if (m.getName().compareTo(currentComponent + "_error") == 0) {
							m.invoke(completude_nocs.this, new Object[] { e, currentComponent, globalMap });
							break;
						}
					}

					if (!(e instanceof TDieException)) {
					}
				} catch (Exception e) {
					this.e.printStackTrace();
				}
			}
		}
	}

	public void tFileInputDelimited_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFilterRow_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tLogRow_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tLogRow_2_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileInputDelimited_3_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_3_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMap_1_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_3_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tLogRow_3_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_3_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tLogRow_4_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_3_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileInputDelimited_2_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_3_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tAdvancedHash_row5_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_3_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileInputDelimited_1_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tFileInputDelimited_3_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public static class row2Struct implements routines.system.IPersistableRow<row2Struct> {
		final static byte[] commonByteArrayLock_PROJETTALEND1_completude_nocs = new byte[0];
		static byte[] commonByteArray_PROJETTALEND1_completude_nocs = new byte[0];

		public String code;

		public String getCode() {
			return this.code;
		}

		public String country;

		public String getCountry() {
			return this.country;
		}

		public String country_long;

		public String getCountry_long() {
			return this.country_long;
		}

		public String tag;

		public String getTag() {
			return this.tag;
		}

		public String note;

		public String getNote() {
			return this.note;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PROJETTALEND1_completude_nocs.length) {
					if (length < 1024 && commonByteArray_PROJETTALEND1_completude_nocs.length == 0) {
						commonByteArray_PROJETTALEND1_completude_nocs = new byte[1024];
					} else {
						commonByteArray_PROJETTALEND1_completude_nocs = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_PROJETTALEND1_completude_nocs, 0, length);
				strReturn = new String(commonByteArray_PROJETTALEND1_completude_nocs, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PROJETTALEND1_completude_nocs.length) {
					if (length < 1024 && commonByteArray_PROJETTALEND1_completude_nocs.length == 0) {
						commonByteArray_PROJETTALEND1_completude_nocs = new byte[1024];
					} else {
						commonByteArray_PROJETTALEND1_completude_nocs = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_PROJETTALEND1_completude_nocs, 0, length);
				strReturn = new String(commonByteArray_PROJETTALEND1_completude_nocs, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_PROJETTALEND1_completude_nocs) {

				try {

					int length = 0;

					this.code = readString(dis);

					this.country = readString(dis);

					this.country_long = readString(dis);

					this.tag = readString(dis);

					this.note = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_PROJETTALEND1_completude_nocs) {

				try {

					int length = 0;

					this.code = readString(dis);

					this.country = readString(dis);

					this.country_long = readString(dis);

					this.tag = readString(dis);

					this.note = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.code, dos);

				// String

				writeString(this.country, dos);

				// String

				writeString(this.country_long, dos);

				// String

				writeString(this.tag, dos);

				// String

				writeString(this.note, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.code, dos);

				// String

				writeString(this.country, dos);

				// String

				writeString(this.country_long, dos);

				// String

				writeString(this.tag, dos);

				// String

				writeString(this.note, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("code=" + code);
			sb.append(",country=" + country);
			sb.append(",country_long=" + country_long);
			sb.append(",tag=" + tag);
			sb.append(",note=" + note);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row2Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row3Struct implements routines.system.IPersistableRow<row3Struct> {
		final static byte[] commonByteArrayLock_PROJETTALEND1_completude_nocs = new byte[0];
		static byte[] commonByteArray_PROJETTALEND1_completude_nocs = new byte[0];

		public String code;

		public String getCode() {
			return this.code;
		}

		public String country;

		public String getCountry() {
			return this.country;
		}

		public String country_long;

		public String getCountry_long() {
			return this.country_long;
		}

		public String tag;

		public String getTag() {
			return this.tag;
		}

		public String note;

		public String getNote() {
			return this.note;
		}

		public String errorMessage;

		public String getErrorMessage() {
			return this.errorMessage;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PROJETTALEND1_completude_nocs.length) {
					if (length < 1024 && commonByteArray_PROJETTALEND1_completude_nocs.length == 0) {
						commonByteArray_PROJETTALEND1_completude_nocs = new byte[1024];
					} else {
						commonByteArray_PROJETTALEND1_completude_nocs = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_PROJETTALEND1_completude_nocs, 0, length);
				strReturn = new String(commonByteArray_PROJETTALEND1_completude_nocs, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PROJETTALEND1_completude_nocs.length) {
					if (length < 1024 && commonByteArray_PROJETTALEND1_completude_nocs.length == 0) {
						commonByteArray_PROJETTALEND1_completude_nocs = new byte[1024];
					} else {
						commonByteArray_PROJETTALEND1_completude_nocs = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_PROJETTALEND1_completude_nocs, 0, length);
				strReturn = new String(commonByteArray_PROJETTALEND1_completude_nocs, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_PROJETTALEND1_completude_nocs) {

				try {

					int length = 0;

					this.code = readString(dis);

					this.country = readString(dis);

					this.country_long = readString(dis);

					this.tag = readString(dis);

					this.note = readString(dis);

					this.errorMessage = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_PROJETTALEND1_completude_nocs) {

				try {

					int length = 0;

					this.code = readString(dis);

					this.country = readString(dis);

					this.country_long = readString(dis);

					this.tag = readString(dis);

					this.note = readString(dis);

					this.errorMessage = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.code, dos);

				// String

				writeString(this.country, dos);

				// String

				writeString(this.country_long, dos);

				// String

				writeString(this.tag, dos);

				// String

				writeString(this.note, dos);

				// String

				writeString(this.errorMessage, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.code, dos);

				// String

				writeString(this.country, dos);

				// String

				writeString(this.country_long, dos);

				// String

				writeString(this.tag, dos);

				// String

				writeString(this.note, dos);

				// String

				writeString(this.errorMessage, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("code=" + code);
			sb.append(",country=" + country);
			sb.append(",country_long=" + country_long);
			sb.append(",tag=" + tag);
			sb.append(",note=" + note);
			sb.append(",errorMessage=" + errorMessage);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row3Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row1Struct implements routines.system.IPersistableRow<row1Struct> {
		final static byte[] commonByteArrayLock_PROJETTALEND1_completude_nocs = new byte[0];
		static byte[] commonByteArray_PROJETTALEND1_completude_nocs = new byte[0];

		public String code;

		public String getCode() {
			return this.code;
		}

		public String country;

		public String getCountry() {
			return this.country;
		}

		public String country_long;

		public String getCountry_long() {
			return this.country_long;
		}

		public String tag;

		public String getTag() {
			return this.tag;
		}

		public String note;

		public String getNote() {
			return this.note;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PROJETTALEND1_completude_nocs.length) {
					if (length < 1024 && commonByteArray_PROJETTALEND1_completude_nocs.length == 0) {
						commonByteArray_PROJETTALEND1_completude_nocs = new byte[1024];
					} else {
						commonByteArray_PROJETTALEND1_completude_nocs = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_PROJETTALEND1_completude_nocs, 0, length);
				strReturn = new String(commonByteArray_PROJETTALEND1_completude_nocs, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PROJETTALEND1_completude_nocs.length) {
					if (length < 1024 && commonByteArray_PROJETTALEND1_completude_nocs.length == 0) {
						commonByteArray_PROJETTALEND1_completude_nocs = new byte[1024];
					} else {
						commonByteArray_PROJETTALEND1_completude_nocs = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_PROJETTALEND1_completude_nocs, 0, length);
				strReturn = new String(commonByteArray_PROJETTALEND1_completude_nocs, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_PROJETTALEND1_completude_nocs) {

				try {

					int length = 0;

					this.code = readString(dis);

					this.country = readString(dis);

					this.country_long = readString(dis);

					this.tag = readString(dis);

					this.note = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_PROJETTALEND1_completude_nocs) {

				try {

					int length = 0;

					this.code = readString(dis);

					this.country = readString(dis);

					this.country_long = readString(dis);

					this.tag = readString(dis);

					this.note = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.code, dos);

				// String

				writeString(this.country, dos);

				// String

				writeString(this.country_long, dos);

				// String

				writeString(this.tag, dos);

				// String

				writeString(this.note, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.code, dos);

				// String

				writeString(this.country, dos);

				// String

				writeString(this.country_long, dos);

				// String

				writeString(this.tag, dos);

				// String

				writeString(this.note, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("code=" + code);
			sb.append(",country=" + country);
			sb.append(",country_long=" + country_long);
			sb.append(",tag=" + tag);
			sb.append(",note=" + note);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row1Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tFileInputDelimited_1Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tFileInputDelimited_1_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";

		String currentComponent = "";
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				row1Struct row1 = new row1Struct();
				row2Struct row2 = new row2Struct();
				row3Struct row3 = new row3Struct();

				/**
				 * [tLogRow_1 begin ] start
				 */

				ok_Hash.put("tLogRow_1", false);
				start_Hash.put("tLogRow_1", System.currentTimeMillis());

				currentComponent = "tLogRow_1";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row2");
				}

				int tos_count_tLogRow_1 = 0;

				///////////////////////

				final String OUTPUT_FIELD_SEPARATOR_tLogRow_1 = "|";
				java.io.PrintStream consoleOut_tLogRow_1 = null;

				StringBuilder strBuffer_tLogRow_1 = null;
				int nb_line_tLogRow_1 = 0;
///////////////////////    			

				/**
				 * [tLogRow_1 begin ] stop
				 */

				/**
				 * [tLogRow_2 begin ] start
				 */

				ok_Hash.put("tLogRow_2", false);
				start_Hash.put("tLogRow_2", System.currentTimeMillis());

				currentComponent = "tLogRow_2";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row3");
				}

				int tos_count_tLogRow_2 = 0;

				///////////////////////

				final String OUTPUT_FIELD_SEPARATOR_tLogRow_2 = "|";
				java.io.PrintStream consoleOut_tLogRow_2 = null;

				StringBuilder strBuffer_tLogRow_2 = null;
				int nb_line_tLogRow_2 = 0;
///////////////////////    			

				/**
				 * [tLogRow_2 begin ] stop
				 */

				/**
				 * [tFilterRow_1 begin ] start
				 */

				ok_Hash.put("tFilterRow_1", false);
				start_Hash.put("tFilterRow_1", System.currentTimeMillis());

				currentComponent = "tFilterRow_1";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row1");
				}

				int tos_count_tFilterRow_1 = 0;

				int nb_line_tFilterRow_1 = 0;
				int nb_line_ok_tFilterRow_1 = 0;
				int nb_line_reject_tFilterRow_1 = 0;

				class Operator_tFilterRow_1 {
					private String sErrorMsg = "";
					private boolean bMatchFlag = true;
					private String sUnionFlag = "&&";

					public Operator_tFilterRow_1(String unionFlag) {
						sUnionFlag = unionFlag;
						bMatchFlag = "||".equals(unionFlag) ? false : true;
					}

					public String getErrorMsg() {
						if (sErrorMsg != null && sErrorMsg.length() > 1)
							return sErrorMsg.substring(1);
						else
							return null;
					}

					public boolean getMatchFlag() {
						return bMatchFlag;
					}

					public void matches(boolean partMatched, String reason) {
						// no need to care about the next judgement
						if ("||".equals(sUnionFlag) && bMatchFlag) {
							return;
						}

						if (!partMatched) {
							sErrorMsg += "|" + reason;
						}

						if ("||".equals(sUnionFlag))
							bMatchFlag = bMatchFlag || partMatched;
						else
							bMatchFlag = bMatchFlag && partMatched;
					}
				}

				/**
				 * [tFilterRow_1 begin ] stop
				 */

				/**
				 * [tFileInputDelimited_1 begin ] start
				 */

				ok_Hash.put("tFileInputDelimited_1", false);
				start_Hash.put("tFileInputDelimited_1", System.currentTimeMillis());

				currentComponent = "tFileInputDelimited_1";

				int tos_count_tFileInputDelimited_1 = 0;

				final routines.system.RowState rowstate_tFileInputDelimited_1 = new routines.system.RowState();

				int nb_line_tFileInputDelimited_1 = 0;
				int footer_tFileInputDelimited_1 = 0;
				int totalLinetFileInputDelimited_1 = 0;
				int limittFileInputDelimited_1 = -1;
				int lastLinetFileInputDelimited_1 = -1;

				char fieldSeparator_tFileInputDelimited_1[] = null;

				// support passing value (property: Field Separator) by 'context.fs' or
				// 'globalMap.get("fs")'.
				if (((String) ",").length() > 0) {
					fieldSeparator_tFileInputDelimited_1 = ((String) ",").toCharArray();
				} else {
					throw new IllegalArgumentException("Field Separator must be assigned a char.");
				}

				char rowSeparator_tFileInputDelimited_1[] = null;

				// support passing value (property: Row Separator) by 'context.rs' or
				// 'globalMap.get("rs")'.
				if (((String) "\n").length() > 0) {
					rowSeparator_tFileInputDelimited_1 = ((String) "\n").toCharArray();
				} else {
					throw new IllegalArgumentException("Row Separator must be assigned a char.");
				}

				Object filename_tFileInputDelimited_1 = /** Start field tFileInputDelimited_1:FILENAME */
						"C:/Users/Alix Lemoine/Documents/M2/S1/Entrepôt de Données/Projet/TP_Donnees_JO_Paris_2024/nocs.csv"/**
																															 * End
																															 * field
																															 * tFileInputDelimited_1:FILENAME
																															 */
				;
				com.talend.csv.CSVReader csvReadertFileInputDelimited_1 = null;

				try {

					String[] rowtFileInputDelimited_1 = null;
					int currentLinetFileInputDelimited_1 = 0;
					int outputLinetFileInputDelimited_1 = 0;
					try {// TD110 begin
						if (filename_tFileInputDelimited_1 instanceof java.io.InputStream) {

							int footer_value_tFileInputDelimited_1 = 0;
							if (footer_value_tFileInputDelimited_1 > 0) {
								throw new java.lang.Exception(
										"When the input source is a stream,footer shouldn't be bigger than 0.");
							}

							csvReadertFileInputDelimited_1 = new com.talend.csv.CSVReader(
									(java.io.InputStream) filename_tFileInputDelimited_1,
									fieldSeparator_tFileInputDelimited_1[0], "UTF-8");
						} else {
							csvReadertFileInputDelimited_1 = new com.talend.csv.CSVReader(
									String.valueOf(filename_tFileInputDelimited_1),
									fieldSeparator_tFileInputDelimited_1[0], "UTF-8");
						}

						csvReadertFileInputDelimited_1.setTrimWhitespace(false);
						if ((rowSeparator_tFileInputDelimited_1[0] != '\n')
								&& (rowSeparator_tFileInputDelimited_1[0] != '\r'))
							csvReadertFileInputDelimited_1.setLineEnd("" + rowSeparator_tFileInputDelimited_1[0]);

						csvReadertFileInputDelimited_1.setQuoteChar('"');

						csvReadertFileInputDelimited_1.setEscapeChar(csvReadertFileInputDelimited_1.getQuoteChar());

						if (footer_tFileInputDelimited_1 > 0) {
							for (totalLinetFileInputDelimited_1 = 0; totalLinetFileInputDelimited_1 < 1; totalLinetFileInputDelimited_1++) {
								csvReadertFileInputDelimited_1.readNext();
							}
							csvReadertFileInputDelimited_1.setSkipEmptyRecords(false);
							while (csvReadertFileInputDelimited_1.readNext()) {

								totalLinetFileInputDelimited_1++;

							}
							int lastLineTemptFileInputDelimited_1 = totalLinetFileInputDelimited_1
									- footer_tFileInputDelimited_1 < 0 ? 0
											: totalLinetFileInputDelimited_1 - footer_tFileInputDelimited_1;
							if (lastLinetFileInputDelimited_1 > 0) {
								lastLinetFileInputDelimited_1 = lastLinetFileInputDelimited_1 < lastLineTemptFileInputDelimited_1
										? lastLinetFileInputDelimited_1
										: lastLineTemptFileInputDelimited_1;
							} else {
								lastLinetFileInputDelimited_1 = lastLineTemptFileInputDelimited_1;
							}

							csvReadertFileInputDelimited_1.close();
							if (filename_tFileInputDelimited_1 instanceof java.io.InputStream) {
								csvReadertFileInputDelimited_1 = new com.talend.csv.CSVReader(
										(java.io.InputStream) filename_tFileInputDelimited_1,
										fieldSeparator_tFileInputDelimited_1[0], "UTF-8");
							} else {
								csvReadertFileInputDelimited_1 = new com.talend.csv.CSVReader(
										String.valueOf(filename_tFileInputDelimited_1),
										fieldSeparator_tFileInputDelimited_1[0], "UTF-8");
							}
							csvReadertFileInputDelimited_1.setTrimWhitespace(false);
							if ((rowSeparator_tFileInputDelimited_1[0] != '\n')
									&& (rowSeparator_tFileInputDelimited_1[0] != '\r'))
								csvReadertFileInputDelimited_1.setLineEnd("" + rowSeparator_tFileInputDelimited_1[0]);

							csvReadertFileInputDelimited_1.setQuoteChar('"');

							csvReadertFileInputDelimited_1.setEscapeChar(csvReadertFileInputDelimited_1.getQuoteChar());

						}

						if (limittFileInputDelimited_1 != 0) {
							for (currentLinetFileInputDelimited_1 = 0; currentLinetFileInputDelimited_1 < 1; currentLinetFileInputDelimited_1++) {
								csvReadertFileInputDelimited_1.readNext();
							}
						}
						csvReadertFileInputDelimited_1.setSkipEmptyRecords(false);

					} catch (java.lang.Exception e) {
						globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE", e.getMessage());

						System.err.println(e.getMessage());

					} // TD110 end

					while (limittFileInputDelimited_1 != 0 && csvReadertFileInputDelimited_1 != null
							&& csvReadertFileInputDelimited_1.readNext()) {
						rowstate_tFileInputDelimited_1.reset();

						rowtFileInputDelimited_1 = csvReadertFileInputDelimited_1.getValues();

						currentLinetFileInputDelimited_1++;

						if (lastLinetFileInputDelimited_1 > -1
								&& currentLinetFileInputDelimited_1 > lastLinetFileInputDelimited_1) {
							break;
						}
						outputLinetFileInputDelimited_1++;
						if (limittFileInputDelimited_1 > 0
								&& outputLinetFileInputDelimited_1 > limittFileInputDelimited_1) {
							break;
						}

						row1 = null;

						boolean whetherReject_tFileInputDelimited_1 = false;
						row1 = new row1Struct();
						try {

							char fieldSeparator_tFileInputDelimited_1_ListType[] = null;
							// support passing value (property: Field Separator) by 'context.fs' or
							// 'globalMap.get("fs")'.
							if (((String) ",").length() > 0) {
								fieldSeparator_tFileInputDelimited_1_ListType = ((String) ",").toCharArray();
							} else {
								throw new IllegalArgumentException("Field Separator must be assigned a char.");
							}
							if (rowtFileInputDelimited_1.length == 1 && ("\015").equals(rowtFileInputDelimited_1[0])) {// empty
																														// line
																														// when
																														// row
																														// separator
																														// is
																														// '\n'

								row1.code = null;

								row1.country = null;

								row1.country_long = null;

								row1.tag = null;

								row1.note = null;

							} else {

								int columnIndexWithD_tFileInputDelimited_1 = 0; // Column Index

								columnIndexWithD_tFileInputDelimited_1 = 0;

								if (columnIndexWithD_tFileInputDelimited_1 < rowtFileInputDelimited_1.length) {

									row1.code = rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1];

								} else {

									row1.code = null;

								}

								columnIndexWithD_tFileInputDelimited_1 = 1;

								if (columnIndexWithD_tFileInputDelimited_1 < rowtFileInputDelimited_1.length) {

									row1.country = rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1];

								} else {

									row1.country = null;

								}

								columnIndexWithD_tFileInputDelimited_1 = 2;

								if (columnIndexWithD_tFileInputDelimited_1 < rowtFileInputDelimited_1.length) {

									row1.country_long = rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1];

								} else {

									row1.country_long = null;

								}

								columnIndexWithD_tFileInputDelimited_1 = 3;

								if (columnIndexWithD_tFileInputDelimited_1 < rowtFileInputDelimited_1.length) {

									row1.tag = rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1];

								} else {

									row1.tag = null;

								}

								columnIndexWithD_tFileInputDelimited_1 = 4;

								if (columnIndexWithD_tFileInputDelimited_1 < rowtFileInputDelimited_1.length) {

									row1.note = rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1];

								} else {

									row1.note = null;

								}

							}

							if (rowstate_tFileInputDelimited_1.getException() != null) {
								throw rowstate_tFileInputDelimited_1.getException();
							}

						} catch (java.lang.Exception e) {
							globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE", e.getMessage());
							whetherReject_tFileInputDelimited_1 = true;

							System.err.println(e.getMessage());
							row1 = null;

							globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE", e.getMessage());

						}

						/**
						 * [tFileInputDelimited_1 begin ] stop
						 */

						/**
						 * [tFileInputDelimited_1 main ] start
						 */

						currentComponent = "tFileInputDelimited_1";

						tos_count_tFileInputDelimited_1++;

						/**
						 * [tFileInputDelimited_1 main ] stop
						 */

						/**
						 * [tFileInputDelimited_1 process_data_begin ] start
						 */

						currentComponent = "tFileInputDelimited_1";

						/**
						 * [tFileInputDelimited_1 process_data_begin ] stop
						 */
// Start of branch "row1"
						if (row1 != null) {
							row3 = null;

							/**
							 * [tFilterRow_1 main ] start
							 */

							currentComponent = "tFilterRow_1";

							if (execStat) {
								runStat.updateStatOnConnection(iterateId, 1, 1

										, "row1"

								);
							}

							row3 = null;
							row2 = null;
							Operator_tFilterRow_1 ope_tFilterRow_1 = new Operator_tFilterRow_1("&&");
							ope_tFilterRow_1.matches((row1.code == null ? false : row1.code.compareTo("") != 0),
									"code.compareTo(\"\") != 0 failed");
							ope_tFilterRow_1.matches((row1.country == null ? false : row1.country.compareTo("") != 0),
									"country.compareTo(\"\") != 0 failed");
							ope_tFilterRow_1.matches(
									(row1.country_long == null ? false : row1.country_long.compareTo("") != 0),
									"country_long.compareTo(\"\") != 0 failed");
							ope_tFilterRow_1.matches((row1.tag == null ? false : row1.tag.compareTo("") != 0),
									"tag.compareTo(\"\") != 0 failed");
							ope_tFilterRow_1.matches((row1.note == null ? false : row1.note.compareTo("") != 0),
									"note.compareTo(\"\") != 0 failed");

							if (ope_tFilterRow_1.getMatchFlag()) {
								if (row2 == null) {
									row2 = new row2Struct();
								}
								row2.code = row1.code;
								row2.country = row1.country;
								row2.country_long = row1.country_long;
								row2.tag = row1.tag;
								row2.note = row1.note;
								nb_line_ok_tFilterRow_1++;
							} else {
								if (row3 == null) {
									row3 = new row3Struct();
								}
								row3.code = row1.code;
								row3.country = row1.country;
								row3.country_long = row1.country_long;
								row3.tag = row1.tag;
								row3.note = row1.note;
								row3.errorMessage = ope_tFilterRow_1.getErrorMsg();
								nb_line_reject_tFilterRow_1++;
							}

							nb_line_tFilterRow_1++;

							tos_count_tFilterRow_1++;

							/**
							 * [tFilterRow_1 main ] stop
							 */

							/**
							 * [tFilterRow_1 process_data_begin ] start
							 */

							currentComponent = "tFilterRow_1";

							/**
							 * [tFilterRow_1 process_data_begin ] stop
							 */
// Start of branch "row2"
							if (row2 != null) {

								/**
								 * [tLogRow_1 main ] start
								 */

								currentComponent = "tLogRow_1";

								if (execStat) {
									runStat.updateStatOnConnection(iterateId, 1, 1

											, "row2"

									);
								}

///////////////////////		

								strBuffer_tLogRow_1 = new StringBuilder();

								if (row2.code != null) { //

									strBuffer_tLogRow_1.append(String.valueOf(row2.code));

								} //

								strBuffer_tLogRow_1.append("|");

								if (row2.country != null) { //

									strBuffer_tLogRow_1.append(String.valueOf(row2.country));

								} //

								strBuffer_tLogRow_1.append("|");

								if (row2.country_long != null) { //

									strBuffer_tLogRow_1.append(String.valueOf(row2.country_long));

								} //

								strBuffer_tLogRow_1.append("|");

								if (row2.tag != null) { //

									strBuffer_tLogRow_1.append(String.valueOf(row2.tag));

								} //

								strBuffer_tLogRow_1.append("|");

								if (row2.note != null) { //

									strBuffer_tLogRow_1.append(String.valueOf(row2.note));

								} //

								if (globalMap.get("tLogRow_CONSOLE") != null) {
									consoleOut_tLogRow_1 = (java.io.PrintStream) globalMap.get("tLogRow_CONSOLE");
								} else {
									consoleOut_tLogRow_1 = new java.io.PrintStream(
											new java.io.BufferedOutputStream(System.out));
									globalMap.put("tLogRow_CONSOLE", consoleOut_tLogRow_1);
								}
								consoleOut_tLogRow_1.println(strBuffer_tLogRow_1.toString());
								consoleOut_tLogRow_1.flush();
								nb_line_tLogRow_1++;
//////

//////                    

///////////////////////    			

								tos_count_tLogRow_1++;

								/**
								 * [tLogRow_1 main ] stop
								 */

								/**
								 * [tLogRow_1 process_data_begin ] start
								 */

								currentComponent = "tLogRow_1";

								/**
								 * [tLogRow_1 process_data_begin ] stop
								 */

								/**
								 * [tLogRow_1 process_data_end ] start
								 */

								currentComponent = "tLogRow_1";

								/**
								 * [tLogRow_1 process_data_end ] stop
								 */

							} // End of branch "row2"

// Start of branch "row3"
							if (row3 != null) {

								/**
								 * [tLogRow_2 main ] start
								 */

								currentComponent = "tLogRow_2";

								if (execStat) {
									runStat.updateStatOnConnection(iterateId, 1, 1

											, "row3"

									);
								}

///////////////////////		

								strBuffer_tLogRow_2 = new StringBuilder();

								if (row3.code != null) { //

									strBuffer_tLogRow_2.append(String.valueOf(row3.code));

								} //

								strBuffer_tLogRow_2.append("|");

								if (row3.country != null) { //

									strBuffer_tLogRow_2.append(String.valueOf(row3.country));

								} //

								strBuffer_tLogRow_2.append("|");

								if (row3.country_long != null) { //

									strBuffer_tLogRow_2.append(String.valueOf(row3.country_long));

								} //

								strBuffer_tLogRow_2.append("|");

								if (row3.tag != null) { //

									strBuffer_tLogRow_2.append(String.valueOf(row3.tag));

								} //

								strBuffer_tLogRow_2.append("|");

								if (row3.note != null) { //

									strBuffer_tLogRow_2.append(String.valueOf(row3.note));

								} //

								strBuffer_tLogRow_2.append("|");

								if (row3.errorMessage != null) { //

									strBuffer_tLogRow_2.append(String.valueOf(row3.errorMessage));

								} //

								if (globalMap.get("tLogRow_CONSOLE") != null) {
									consoleOut_tLogRow_2 = (java.io.PrintStream) globalMap.get("tLogRow_CONSOLE");
								} else {
									consoleOut_tLogRow_2 = new java.io.PrintStream(
											new java.io.BufferedOutputStream(System.out));
									globalMap.put("tLogRow_CONSOLE", consoleOut_tLogRow_2);
								}
								consoleOut_tLogRow_2.println(strBuffer_tLogRow_2.toString());
								consoleOut_tLogRow_2.flush();
								nb_line_tLogRow_2++;
//////

//////                    

///////////////////////    			

								tos_count_tLogRow_2++;

								/**
								 * [tLogRow_2 main ] stop
								 */

								/**
								 * [tLogRow_2 process_data_begin ] start
								 */

								currentComponent = "tLogRow_2";

								/**
								 * [tLogRow_2 process_data_begin ] stop
								 */

								/**
								 * [tLogRow_2 process_data_end ] start
								 */

								currentComponent = "tLogRow_2";

								/**
								 * [tLogRow_2 process_data_end ] stop
								 */

							} // End of branch "row3"

							/**
							 * [tFilterRow_1 process_data_end ] start
							 */

							currentComponent = "tFilterRow_1";

							/**
							 * [tFilterRow_1 process_data_end ] stop
							 */

						} // End of branch "row1"

						/**
						 * [tFileInputDelimited_1 process_data_end ] start
						 */

						currentComponent = "tFileInputDelimited_1";

						/**
						 * [tFileInputDelimited_1 process_data_end ] stop
						 */

						/**
						 * [tFileInputDelimited_1 end ] start
						 */

						currentComponent = "tFileInputDelimited_1";

						nb_line_tFileInputDelimited_1++;
					}

				} finally {
					if (!(filename_tFileInputDelimited_1 instanceof java.io.InputStream)) {
						if (csvReadertFileInputDelimited_1 != null) {
							csvReadertFileInputDelimited_1.close();
						}
					}
					if (csvReadertFileInputDelimited_1 != null) {
						globalMap.put("tFileInputDelimited_1_NB_LINE", nb_line_tFileInputDelimited_1);
					}

				}

				ok_Hash.put("tFileInputDelimited_1", true);
				end_Hash.put("tFileInputDelimited_1", System.currentTimeMillis());

				/**
				 * [tFileInputDelimited_1 end ] stop
				 */

				/**
				 * [tFilterRow_1 end ] start
				 */

				currentComponent = "tFilterRow_1";

				globalMap.put("tFilterRow_1_NB_LINE", nb_line_tFilterRow_1);
				globalMap.put("tFilterRow_1_NB_LINE_OK", nb_line_ok_tFilterRow_1);
				globalMap.put("tFilterRow_1_NB_LINE_REJECT", nb_line_reject_tFilterRow_1);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row1");
				}

				ok_Hash.put("tFilterRow_1", true);
				end_Hash.put("tFilterRow_1", System.currentTimeMillis());

				/**
				 * [tFilterRow_1 end ] stop
				 */

				/**
				 * [tLogRow_1 end ] start
				 */

				currentComponent = "tLogRow_1";

//////
//////
				globalMap.put("tLogRow_1_NB_LINE", nb_line_tLogRow_1);

///////////////////////    			

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row2");
				}

				ok_Hash.put("tLogRow_1", true);
				end_Hash.put("tLogRow_1", System.currentTimeMillis());

				/**
				 * [tLogRow_1 end ] stop
				 */

				/**
				 * [tLogRow_2 end ] start
				 */

				currentComponent = "tLogRow_2";

//////
//////
				globalMap.put("tLogRow_2_NB_LINE", nb_line_tLogRow_2);

///////////////////////    			

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row3");
				}

				ok_Hash.put("tLogRow_2", true);
				end_Hash.put("tLogRow_2", System.currentTimeMillis());

				/**
				 * [tLogRow_2 end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [tFileInputDelimited_1 finally ] start
				 */

				currentComponent = "tFileInputDelimited_1";

				/**
				 * [tFileInputDelimited_1 finally ] stop
				 */

				/**
				 * [tFilterRow_1 finally ] start
				 */

				currentComponent = "tFilterRow_1";

				/**
				 * [tFilterRow_1 finally ] stop
				 */

				/**
				 * [tLogRow_1 finally ] start
				 */

				currentComponent = "tLogRow_1";

				/**
				 * [tLogRow_1 finally ] stop
				 */

				/**
				 * [tLogRow_2 finally ] start
				 */

				currentComponent = "tLogRow_2";

				/**
				 * [tLogRow_2 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tFileInputDelimited_1_SUBPROCESS_STATE", 1);
	}

	public static class out1Struct implements routines.system.IPersistableRow<out1Struct> {
		final static byte[] commonByteArrayLock_PROJETTALEND1_completude_nocs = new byte[0];
		static byte[] commonByteArray_PROJETTALEND1_completude_nocs = new byte[0];

		public int code_athlete;

		public int getCode_athlete() {
			return this.code_athlete;
		}

		public String code_pays;

		public String getCode_pays() {
			return this.code_pays;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PROJETTALEND1_completude_nocs.length) {
					if (length < 1024 && commonByteArray_PROJETTALEND1_completude_nocs.length == 0) {
						commonByteArray_PROJETTALEND1_completude_nocs = new byte[1024];
					} else {
						commonByteArray_PROJETTALEND1_completude_nocs = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_PROJETTALEND1_completude_nocs, 0, length);
				strReturn = new String(commonByteArray_PROJETTALEND1_completude_nocs, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PROJETTALEND1_completude_nocs.length) {
					if (length < 1024 && commonByteArray_PROJETTALEND1_completude_nocs.length == 0) {
						commonByteArray_PROJETTALEND1_completude_nocs = new byte[1024];
					} else {
						commonByteArray_PROJETTALEND1_completude_nocs = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_PROJETTALEND1_completude_nocs, 0, length);
				strReturn = new String(commonByteArray_PROJETTALEND1_completude_nocs, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_PROJETTALEND1_completude_nocs) {

				try {

					int length = 0;

					this.code_athlete = dis.readInt();

					this.code_pays = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_PROJETTALEND1_completude_nocs) {

				try {

					int length = 0;

					this.code_athlete = dis.readInt();

					this.code_pays = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// int

				dos.writeInt(this.code_athlete);

				// String

				writeString(this.code_pays, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// int

				dos.writeInt(this.code_athlete);

				// String

				writeString(this.code_pays, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("code_athlete=" + String.valueOf(code_athlete));
			sb.append(",code_pays=" + code_pays);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(out1Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class out2Struct implements routines.system.IPersistableRow<out2Struct> {
		final static byte[] commonByteArrayLock_PROJETTALEND1_completude_nocs = new byte[0];
		static byte[] commonByteArray_PROJETTALEND1_completude_nocs = new byte[0];

		public Integer code_athlete;

		public Integer getCode_athlete() {
			return this.code_athlete;
		}

		public String code_pays;

		public String getCode_pays() {
			return this.code_pays;
		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PROJETTALEND1_completude_nocs.length) {
					if (length < 1024 && commonByteArray_PROJETTALEND1_completude_nocs.length == 0) {
						commonByteArray_PROJETTALEND1_completude_nocs = new byte[1024];
					} else {
						commonByteArray_PROJETTALEND1_completude_nocs = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_PROJETTALEND1_completude_nocs, 0, length);
				strReturn = new String(commonByteArray_PROJETTALEND1_completude_nocs, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PROJETTALEND1_completude_nocs.length) {
					if (length < 1024 && commonByteArray_PROJETTALEND1_completude_nocs.length == 0) {
						commonByteArray_PROJETTALEND1_completude_nocs = new byte[1024];
					} else {
						commonByteArray_PROJETTALEND1_completude_nocs = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_PROJETTALEND1_completude_nocs, 0, length);
				strReturn = new String(commonByteArray_PROJETTALEND1_completude_nocs, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_PROJETTALEND1_completude_nocs) {

				try {

					int length = 0;

					this.code_athlete = readInteger(dis);

					this.code_pays = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_PROJETTALEND1_completude_nocs) {

				try {

					int length = 0;

					this.code_athlete = readInteger(dis);

					this.code_pays = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.code_athlete, dos);

				// String

				writeString(this.code_pays, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.code_athlete, dos);

				// String

				writeString(this.code_pays, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("code_athlete=" + String.valueOf(code_athlete));
			sb.append(",code_pays=" + code_pays);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(out2Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row4Struct implements routines.system.IPersistableRow<row4Struct> {
		final static byte[] commonByteArrayLock_PROJETTALEND1_completude_nocs = new byte[0];
		static byte[] commonByteArray_PROJETTALEND1_completude_nocs = new byte[0];

		public Integer code;

		public Integer getCode() {
			return this.code;
		}

		public String current;

		public String getCurrent() {
			return this.current;
		}

		public String name;

		public String getName() {
			return this.name;
		}

		public String name_short;

		public String getName_short() {
			return this.name_short;
		}

		public String name_tv;

		public String getName_tv() {
			return this.name_tv;
		}

		public String gender;

		public String getGender() {
			return this.gender;
		}

		public String function;

		public String getFunction() {
			return this.function;
		}

		public String country_code;

		public String getCountry_code() {
			return this.country_code;
		}

		public String country;

		public String getCountry() {
			return this.country;
		}

		public String country_long;

		public String getCountry_long() {
			return this.country_long;
		}

		public String nationality;

		public String getNationality() {
			return this.nationality;
		}

		public String nationality_long;

		public String getNationality_long() {
			return this.nationality_long;
		}

		public String nationality_code;

		public String getNationality_code() {
			return this.nationality_code;
		}

		public Float height;

		public Float getHeight() {
			return this.height;
		}

		public Float weight;

		public Float getWeight() {
			return this.weight;
		}

		public String disciplines;

		public String getDisciplines() {
			return this.disciplines;
		}

		public String events;

		public String getEvents() {
			return this.events;
		}

		public java.util.Date birth_date;

		public java.util.Date getBirth_date() {
			return this.birth_date;
		}

		public String birth_place;

		public String getBirth_place() {
			return this.birth_place;
		}

		public String birth_country;

		public String getBirth_country() {
			return this.birth_country;
		}

		public String residence_place;

		public String getResidence_place() {
			return this.residence_place;
		}

		public String residence_country;

		public String getResidence_country() {
			return this.residence_country;
		}

		public String nickname;

		public String getNickname() {
			return this.nickname;
		}

		public String hobbies;

		public String getHobbies() {
			return this.hobbies;
		}

		public String occupation;

		public String getOccupation() {
			return this.occupation;
		}

		public String education;

		public String getEducation() {
			return this.education;
		}

		public String family;

		public String getFamily() {
			return this.family;
		}

		public String lang;

		public String getLang() {
			return this.lang;
		}

		public String coach;

		public String getCoach() {
			return this.coach;
		}

		public String reason;

		public String getReason() {
			return this.reason;
		}

		public String hero;

		public String getHero() {
			return this.hero;
		}

		public String influence;

		public String getInfluence() {
			return this.influence;
		}

		public String philosophy;

		public String getPhilosophy() {
			return this.philosophy;
		}

		public String sporting_relatives;

		public String getSporting_relatives() {
			return this.sporting_relatives;
		}

		public String ritual;

		public String getRitual() {
			return this.ritual;
		}

		public String other_sports;

		public String getOther_sports() {
			return this.other_sports;
		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PROJETTALEND1_completude_nocs.length) {
					if (length < 1024 && commonByteArray_PROJETTALEND1_completude_nocs.length == 0) {
						commonByteArray_PROJETTALEND1_completude_nocs = new byte[1024];
					} else {
						commonByteArray_PROJETTALEND1_completude_nocs = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_PROJETTALEND1_completude_nocs, 0, length);
				strReturn = new String(commonByteArray_PROJETTALEND1_completude_nocs, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PROJETTALEND1_completude_nocs.length) {
					if (length < 1024 && commonByteArray_PROJETTALEND1_completude_nocs.length == 0) {
						commonByteArray_PROJETTALEND1_completude_nocs = new byte[1024];
					} else {
						commonByteArray_PROJETTALEND1_completude_nocs = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_PROJETTALEND1_completude_nocs, 0, length);
				strReturn = new String(commonByteArray_PROJETTALEND1_completude_nocs, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		private java.util.Date readDate(ObjectInputStream dis) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(dis.readLong());
			}
			return dateReturn;
		}

		private java.util.Date readDate(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = unmarshaller.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(unmarshaller.readLong());
			}
			return dateReturn;
		}

		private void writeDate(java.util.Date date1, ObjectOutputStream dos) throws IOException {
			if (date1 == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeLong(date1.getTime());
			}
		}

		private void writeDate(java.util.Date date1, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (date1 == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeLong(date1.getTime());
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_PROJETTALEND1_completude_nocs) {

				try {

					int length = 0;

					this.code = readInteger(dis);

					this.current = readString(dis);

					this.name = readString(dis);

					this.name_short = readString(dis);

					this.name_tv = readString(dis);

					this.gender = readString(dis);

					this.function = readString(dis);

					this.country_code = readString(dis);

					this.country = readString(dis);

					this.country_long = readString(dis);

					this.nationality = readString(dis);

					this.nationality_long = readString(dis);

					this.nationality_code = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.height = null;
					} else {
						this.height = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.weight = null;
					} else {
						this.weight = dis.readFloat();
					}

					this.disciplines = readString(dis);

					this.events = readString(dis);

					this.birth_date = readDate(dis);

					this.birth_place = readString(dis);

					this.birth_country = readString(dis);

					this.residence_place = readString(dis);

					this.residence_country = readString(dis);

					this.nickname = readString(dis);

					this.hobbies = readString(dis);

					this.occupation = readString(dis);

					this.education = readString(dis);

					this.family = readString(dis);

					this.lang = readString(dis);

					this.coach = readString(dis);

					this.reason = readString(dis);

					this.hero = readString(dis);

					this.influence = readString(dis);

					this.philosophy = readString(dis);

					this.sporting_relatives = readString(dis);

					this.ritual = readString(dis);

					this.other_sports = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_PROJETTALEND1_completude_nocs) {

				try {

					int length = 0;

					this.code = readInteger(dis);

					this.current = readString(dis);

					this.name = readString(dis);

					this.name_short = readString(dis);

					this.name_tv = readString(dis);

					this.gender = readString(dis);

					this.function = readString(dis);

					this.country_code = readString(dis);

					this.country = readString(dis);

					this.country_long = readString(dis);

					this.nationality = readString(dis);

					this.nationality_long = readString(dis);

					this.nationality_code = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.height = null;
					} else {
						this.height = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.weight = null;
					} else {
						this.weight = dis.readFloat();
					}

					this.disciplines = readString(dis);

					this.events = readString(dis);

					this.birth_date = readDate(dis);

					this.birth_place = readString(dis);

					this.birth_country = readString(dis);

					this.residence_place = readString(dis);

					this.residence_country = readString(dis);

					this.nickname = readString(dis);

					this.hobbies = readString(dis);

					this.occupation = readString(dis);

					this.education = readString(dis);

					this.family = readString(dis);

					this.lang = readString(dis);

					this.coach = readString(dis);

					this.reason = readString(dis);

					this.hero = readString(dis);

					this.influence = readString(dis);

					this.philosophy = readString(dis);

					this.sporting_relatives = readString(dis);

					this.ritual = readString(dis);

					this.other_sports = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.code, dos);

				// String

				writeString(this.current, dos);

				// String

				writeString(this.name, dos);

				// String

				writeString(this.name_short, dos);

				// String

				writeString(this.name_tv, dos);

				// String

				writeString(this.gender, dos);

				// String

				writeString(this.function, dos);

				// String

				writeString(this.country_code, dos);

				// String

				writeString(this.country, dos);

				// String

				writeString(this.country_long, dos);

				// String

				writeString(this.nationality, dos);

				// String

				writeString(this.nationality_long, dos);

				// String

				writeString(this.nationality_code, dos);

				// Float

				if (this.height == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.height);
				}

				// Float

				if (this.weight == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.weight);
				}

				// String

				writeString(this.disciplines, dos);

				// String

				writeString(this.events, dos);

				// java.util.Date

				writeDate(this.birth_date, dos);

				// String

				writeString(this.birth_place, dos);

				// String

				writeString(this.birth_country, dos);

				// String

				writeString(this.residence_place, dos);

				// String

				writeString(this.residence_country, dos);

				// String

				writeString(this.nickname, dos);

				// String

				writeString(this.hobbies, dos);

				// String

				writeString(this.occupation, dos);

				// String

				writeString(this.education, dos);

				// String

				writeString(this.family, dos);

				// String

				writeString(this.lang, dos);

				// String

				writeString(this.coach, dos);

				// String

				writeString(this.reason, dos);

				// String

				writeString(this.hero, dos);

				// String

				writeString(this.influence, dos);

				// String

				writeString(this.philosophy, dos);

				// String

				writeString(this.sporting_relatives, dos);

				// String

				writeString(this.ritual, dos);

				// String

				writeString(this.other_sports, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.code, dos);

				// String

				writeString(this.current, dos);

				// String

				writeString(this.name, dos);

				// String

				writeString(this.name_short, dos);

				// String

				writeString(this.name_tv, dos);

				// String

				writeString(this.gender, dos);

				// String

				writeString(this.function, dos);

				// String

				writeString(this.country_code, dos);

				// String

				writeString(this.country, dos);

				// String

				writeString(this.country_long, dos);

				// String

				writeString(this.nationality, dos);

				// String

				writeString(this.nationality_long, dos);

				// String

				writeString(this.nationality_code, dos);

				// Float

				if (this.height == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.height);
				}

				// Float

				if (this.weight == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.weight);
				}

				// String

				writeString(this.disciplines, dos);

				// String

				writeString(this.events, dos);

				// java.util.Date

				writeDate(this.birth_date, dos);

				// String

				writeString(this.birth_place, dos);

				// String

				writeString(this.birth_country, dos);

				// String

				writeString(this.residence_place, dos);

				// String

				writeString(this.residence_country, dos);

				// String

				writeString(this.nickname, dos);

				// String

				writeString(this.hobbies, dos);

				// String

				writeString(this.occupation, dos);

				// String

				writeString(this.education, dos);

				// String

				writeString(this.family, dos);

				// String

				writeString(this.lang, dos);

				// String

				writeString(this.coach, dos);

				// String

				writeString(this.reason, dos);

				// String

				writeString(this.hero, dos);

				// String

				writeString(this.influence, dos);

				// String

				writeString(this.philosophy, dos);

				// String

				writeString(this.sporting_relatives, dos);

				// String

				writeString(this.ritual, dos);

				// String

				writeString(this.other_sports, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("code=" + String.valueOf(code));
			sb.append(",current=" + current);
			sb.append(",name=" + name);
			sb.append(",name_short=" + name_short);
			sb.append(",name_tv=" + name_tv);
			sb.append(",gender=" + gender);
			sb.append(",function=" + function);
			sb.append(",country_code=" + country_code);
			sb.append(",country=" + country);
			sb.append(",country_long=" + country_long);
			sb.append(",nationality=" + nationality);
			sb.append(",nationality_long=" + nationality_long);
			sb.append(",nationality_code=" + nationality_code);
			sb.append(",height=" + String.valueOf(height));
			sb.append(",weight=" + String.valueOf(weight));
			sb.append(",disciplines=" + disciplines);
			sb.append(",events=" + events);
			sb.append(",birth_date=" + String.valueOf(birth_date));
			sb.append(",birth_place=" + birth_place);
			sb.append(",birth_country=" + birth_country);
			sb.append(",residence_place=" + residence_place);
			sb.append(",residence_country=" + residence_country);
			sb.append(",nickname=" + nickname);
			sb.append(",hobbies=" + hobbies);
			sb.append(",occupation=" + occupation);
			sb.append(",education=" + education);
			sb.append(",family=" + family);
			sb.append(",lang=" + lang);
			sb.append(",coach=" + coach);
			sb.append(",reason=" + reason);
			sb.append(",hero=" + hero);
			sb.append(",influence=" + influence);
			sb.append(",philosophy=" + philosophy);
			sb.append(",sporting_relatives=" + sporting_relatives);
			sb.append(",ritual=" + ritual);
			sb.append(",other_sports=" + other_sports);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row4Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class after_tFileInputDelimited_3Struct
			implements routines.system.IPersistableRow<after_tFileInputDelimited_3Struct> {
		final static byte[] commonByteArrayLock_PROJETTALEND1_completude_nocs = new byte[0];
		static byte[] commonByteArray_PROJETTALEND1_completude_nocs = new byte[0];

		public Integer code;

		public Integer getCode() {
			return this.code;
		}

		public String current;

		public String getCurrent() {
			return this.current;
		}

		public String name;

		public String getName() {
			return this.name;
		}

		public String name_short;

		public String getName_short() {
			return this.name_short;
		}

		public String name_tv;

		public String getName_tv() {
			return this.name_tv;
		}

		public String gender;

		public String getGender() {
			return this.gender;
		}

		public String function;

		public String getFunction() {
			return this.function;
		}

		public String country_code;

		public String getCountry_code() {
			return this.country_code;
		}

		public String country;

		public String getCountry() {
			return this.country;
		}

		public String country_long;

		public String getCountry_long() {
			return this.country_long;
		}

		public String nationality;

		public String getNationality() {
			return this.nationality;
		}

		public String nationality_long;

		public String getNationality_long() {
			return this.nationality_long;
		}

		public String nationality_code;

		public String getNationality_code() {
			return this.nationality_code;
		}

		public Float height;

		public Float getHeight() {
			return this.height;
		}

		public Float weight;

		public Float getWeight() {
			return this.weight;
		}

		public String disciplines;

		public String getDisciplines() {
			return this.disciplines;
		}

		public String events;

		public String getEvents() {
			return this.events;
		}

		public java.util.Date birth_date;

		public java.util.Date getBirth_date() {
			return this.birth_date;
		}

		public String birth_place;

		public String getBirth_place() {
			return this.birth_place;
		}

		public String birth_country;

		public String getBirth_country() {
			return this.birth_country;
		}

		public String residence_place;

		public String getResidence_place() {
			return this.residence_place;
		}

		public String residence_country;

		public String getResidence_country() {
			return this.residence_country;
		}

		public String nickname;

		public String getNickname() {
			return this.nickname;
		}

		public String hobbies;

		public String getHobbies() {
			return this.hobbies;
		}

		public String occupation;

		public String getOccupation() {
			return this.occupation;
		}

		public String education;

		public String getEducation() {
			return this.education;
		}

		public String family;

		public String getFamily() {
			return this.family;
		}

		public String lang;

		public String getLang() {
			return this.lang;
		}

		public String coach;

		public String getCoach() {
			return this.coach;
		}

		public String reason;

		public String getReason() {
			return this.reason;
		}

		public String hero;

		public String getHero() {
			return this.hero;
		}

		public String influence;

		public String getInfluence() {
			return this.influence;
		}

		public String philosophy;

		public String getPhilosophy() {
			return this.philosophy;
		}

		public String sporting_relatives;

		public String getSporting_relatives() {
			return this.sporting_relatives;
		}

		public String ritual;

		public String getRitual() {
			return this.ritual;
		}

		public String other_sports;

		public String getOther_sports() {
			return this.other_sports;
		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PROJETTALEND1_completude_nocs.length) {
					if (length < 1024 && commonByteArray_PROJETTALEND1_completude_nocs.length == 0) {
						commonByteArray_PROJETTALEND1_completude_nocs = new byte[1024];
					} else {
						commonByteArray_PROJETTALEND1_completude_nocs = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_PROJETTALEND1_completude_nocs, 0, length);
				strReturn = new String(commonByteArray_PROJETTALEND1_completude_nocs, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PROJETTALEND1_completude_nocs.length) {
					if (length < 1024 && commonByteArray_PROJETTALEND1_completude_nocs.length == 0) {
						commonByteArray_PROJETTALEND1_completude_nocs = new byte[1024];
					} else {
						commonByteArray_PROJETTALEND1_completude_nocs = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_PROJETTALEND1_completude_nocs, 0, length);
				strReturn = new String(commonByteArray_PROJETTALEND1_completude_nocs, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		private java.util.Date readDate(ObjectInputStream dis) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(dis.readLong());
			}
			return dateReturn;
		}

		private java.util.Date readDate(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = unmarshaller.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(unmarshaller.readLong());
			}
			return dateReturn;
		}

		private void writeDate(java.util.Date date1, ObjectOutputStream dos) throws IOException {
			if (date1 == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeLong(date1.getTime());
			}
		}

		private void writeDate(java.util.Date date1, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (date1 == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeLong(date1.getTime());
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_PROJETTALEND1_completude_nocs) {

				try {

					int length = 0;

					this.code = readInteger(dis);

					this.current = readString(dis);

					this.name = readString(dis);

					this.name_short = readString(dis);

					this.name_tv = readString(dis);

					this.gender = readString(dis);

					this.function = readString(dis);

					this.country_code = readString(dis);

					this.country = readString(dis);

					this.country_long = readString(dis);

					this.nationality = readString(dis);

					this.nationality_long = readString(dis);

					this.nationality_code = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.height = null;
					} else {
						this.height = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.weight = null;
					} else {
						this.weight = dis.readFloat();
					}

					this.disciplines = readString(dis);

					this.events = readString(dis);

					this.birth_date = readDate(dis);

					this.birth_place = readString(dis);

					this.birth_country = readString(dis);

					this.residence_place = readString(dis);

					this.residence_country = readString(dis);

					this.nickname = readString(dis);

					this.hobbies = readString(dis);

					this.occupation = readString(dis);

					this.education = readString(dis);

					this.family = readString(dis);

					this.lang = readString(dis);

					this.coach = readString(dis);

					this.reason = readString(dis);

					this.hero = readString(dis);

					this.influence = readString(dis);

					this.philosophy = readString(dis);

					this.sporting_relatives = readString(dis);

					this.ritual = readString(dis);

					this.other_sports = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_PROJETTALEND1_completude_nocs) {

				try {

					int length = 0;

					this.code = readInteger(dis);

					this.current = readString(dis);

					this.name = readString(dis);

					this.name_short = readString(dis);

					this.name_tv = readString(dis);

					this.gender = readString(dis);

					this.function = readString(dis);

					this.country_code = readString(dis);

					this.country = readString(dis);

					this.country_long = readString(dis);

					this.nationality = readString(dis);

					this.nationality_long = readString(dis);

					this.nationality_code = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.height = null;
					} else {
						this.height = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.weight = null;
					} else {
						this.weight = dis.readFloat();
					}

					this.disciplines = readString(dis);

					this.events = readString(dis);

					this.birth_date = readDate(dis);

					this.birth_place = readString(dis);

					this.birth_country = readString(dis);

					this.residence_place = readString(dis);

					this.residence_country = readString(dis);

					this.nickname = readString(dis);

					this.hobbies = readString(dis);

					this.occupation = readString(dis);

					this.education = readString(dis);

					this.family = readString(dis);

					this.lang = readString(dis);

					this.coach = readString(dis);

					this.reason = readString(dis);

					this.hero = readString(dis);

					this.influence = readString(dis);

					this.philosophy = readString(dis);

					this.sporting_relatives = readString(dis);

					this.ritual = readString(dis);

					this.other_sports = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.code, dos);

				// String

				writeString(this.current, dos);

				// String

				writeString(this.name, dos);

				// String

				writeString(this.name_short, dos);

				// String

				writeString(this.name_tv, dos);

				// String

				writeString(this.gender, dos);

				// String

				writeString(this.function, dos);

				// String

				writeString(this.country_code, dos);

				// String

				writeString(this.country, dos);

				// String

				writeString(this.country_long, dos);

				// String

				writeString(this.nationality, dos);

				// String

				writeString(this.nationality_long, dos);

				// String

				writeString(this.nationality_code, dos);

				// Float

				if (this.height == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.height);
				}

				// Float

				if (this.weight == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.weight);
				}

				// String

				writeString(this.disciplines, dos);

				// String

				writeString(this.events, dos);

				// java.util.Date

				writeDate(this.birth_date, dos);

				// String

				writeString(this.birth_place, dos);

				// String

				writeString(this.birth_country, dos);

				// String

				writeString(this.residence_place, dos);

				// String

				writeString(this.residence_country, dos);

				// String

				writeString(this.nickname, dos);

				// String

				writeString(this.hobbies, dos);

				// String

				writeString(this.occupation, dos);

				// String

				writeString(this.education, dos);

				// String

				writeString(this.family, dos);

				// String

				writeString(this.lang, dos);

				// String

				writeString(this.coach, dos);

				// String

				writeString(this.reason, dos);

				// String

				writeString(this.hero, dos);

				// String

				writeString(this.influence, dos);

				// String

				writeString(this.philosophy, dos);

				// String

				writeString(this.sporting_relatives, dos);

				// String

				writeString(this.ritual, dos);

				// String

				writeString(this.other_sports, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.code, dos);

				// String

				writeString(this.current, dos);

				// String

				writeString(this.name, dos);

				// String

				writeString(this.name_short, dos);

				// String

				writeString(this.name_tv, dos);

				// String

				writeString(this.gender, dos);

				// String

				writeString(this.function, dos);

				// String

				writeString(this.country_code, dos);

				// String

				writeString(this.country, dos);

				// String

				writeString(this.country_long, dos);

				// String

				writeString(this.nationality, dos);

				// String

				writeString(this.nationality_long, dos);

				// String

				writeString(this.nationality_code, dos);

				// Float

				if (this.height == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.height);
				}

				// Float

				if (this.weight == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.weight);
				}

				// String

				writeString(this.disciplines, dos);

				// String

				writeString(this.events, dos);

				// java.util.Date

				writeDate(this.birth_date, dos);

				// String

				writeString(this.birth_place, dos);

				// String

				writeString(this.birth_country, dos);

				// String

				writeString(this.residence_place, dos);

				// String

				writeString(this.residence_country, dos);

				// String

				writeString(this.nickname, dos);

				// String

				writeString(this.hobbies, dos);

				// String

				writeString(this.occupation, dos);

				// String

				writeString(this.education, dos);

				// String

				writeString(this.family, dos);

				// String

				writeString(this.lang, dos);

				// String

				writeString(this.coach, dos);

				// String

				writeString(this.reason, dos);

				// String

				writeString(this.hero, dos);

				// String

				writeString(this.influence, dos);

				// String

				writeString(this.philosophy, dos);

				// String

				writeString(this.sporting_relatives, dos);

				// String

				writeString(this.ritual, dos);

				// String

				writeString(this.other_sports, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("code=" + String.valueOf(code));
			sb.append(",current=" + current);
			sb.append(",name=" + name);
			sb.append(",name_short=" + name_short);
			sb.append(",name_tv=" + name_tv);
			sb.append(",gender=" + gender);
			sb.append(",function=" + function);
			sb.append(",country_code=" + country_code);
			sb.append(",country=" + country);
			sb.append(",country_long=" + country_long);
			sb.append(",nationality=" + nationality);
			sb.append(",nationality_long=" + nationality_long);
			sb.append(",nationality_code=" + nationality_code);
			sb.append(",height=" + String.valueOf(height));
			sb.append(",weight=" + String.valueOf(weight));
			sb.append(",disciplines=" + disciplines);
			sb.append(",events=" + events);
			sb.append(",birth_date=" + String.valueOf(birth_date));
			sb.append(",birth_place=" + birth_place);
			sb.append(",birth_country=" + birth_country);
			sb.append(",residence_place=" + residence_place);
			sb.append(",residence_country=" + residence_country);
			sb.append(",nickname=" + nickname);
			sb.append(",hobbies=" + hobbies);
			sb.append(",occupation=" + occupation);
			sb.append(",education=" + education);
			sb.append(",family=" + family);
			sb.append(",lang=" + lang);
			sb.append(",coach=" + coach);
			sb.append(",reason=" + reason);
			sb.append(",hero=" + hero);
			sb.append(",influence=" + influence);
			sb.append(",philosophy=" + philosophy);
			sb.append(",sporting_relatives=" + sporting_relatives);
			sb.append(",ritual=" + ritual);
			sb.append(",other_sports=" + other_sports);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(after_tFileInputDelimited_3Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tFileInputDelimited_3Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tFileInputDelimited_3_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";

		String currentComponent = "";
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				tFileInputDelimited_2Process(globalMap);

				row4Struct row4 = new row4Struct();
				out1Struct out1 = new out1Struct();
				out2Struct out2 = new out2Struct();

				/**
				 * [tLogRow_3 begin ] start
				 */

				ok_Hash.put("tLogRow_3", false);
				start_Hash.put("tLogRow_3", System.currentTimeMillis());

				currentComponent = "tLogRow_3";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "out1");
				}

				int tos_count_tLogRow_3 = 0;

				///////////////////////

				final String OUTPUT_FIELD_SEPARATOR_tLogRow_3 = "|";
				java.io.PrintStream consoleOut_tLogRow_3 = null;

				StringBuilder strBuffer_tLogRow_3 = null;
				int nb_line_tLogRow_3 = 0;
///////////////////////    			

				/**
				 * [tLogRow_3 begin ] stop
				 */

				/**
				 * [tLogRow_4 begin ] start
				 */

				ok_Hash.put("tLogRow_4", false);
				start_Hash.put("tLogRow_4", System.currentTimeMillis());

				currentComponent = "tLogRow_4";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "out2");
				}

				int tos_count_tLogRow_4 = 0;

				///////////////////////

				final String OUTPUT_FIELD_SEPARATOR_tLogRow_4 = "|";
				java.io.PrintStream consoleOut_tLogRow_4 = null;

				StringBuilder strBuffer_tLogRow_4 = null;
				int nb_line_tLogRow_4 = 0;
///////////////////////    			

				/**
				 * [tLogRow_4 begin ] stop
				 */

				/**
				 * [tMap_1 begin ] start
				 */

				ok_Hash.put("tMap_1", false);
				start_Hash.put("tMap_1", System.currentTimeMillis());

				currentComponent = "tMap_1";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row4");
				}

				int tos_count_tMap_1 = 0;

// ###############################
// # Lookup's keys initialization

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row5Struct> tHash_Lookup_row5 = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row5Struct>) ((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row5Struct>) globalMap
						.get("tHash_Lookup_row5"));

				row5Struct row5HashKey = new row5Struct();
				row5Struct row5Default = new row5Struct();
// ###############################        

// ###############################
// # Vars initialization
				class Var__tMap_1__Struct {
				}
				Var__tMap_1__Struct Var__tMap_1 = new Var__tMap_1__Struct();
// ###############################

// ###############################
// # Outputs initialization
				out1Struct out1_tmp = new out1Struct();
				out2Struct out2_tmp = new out2Struct();
// ###############################

				/**
				 * [tMap_1 begin ] stop
				 */

				/**
				 * [tFileInputDelimited_3 begin ] start
				 */

				ok_Hash.put("tFileInputDelimited_3", false);
				start_Hash.put("tFileInputDelimited_3", System.currentTimeMillis());

				currentComponent = "tFileInputDelimited_3";

				int tos_count_tFileInputDelimited_3 = 0;

				final routines.system.RowState rowstate_tFileInputDelimited_3 = new routines.system.RowState();

				int nb_line_tFileInputDelimited_3 = 0;
				int footer_tFileInputDelimited_3 = 0;
				int totalLinetFileInputDelimited_3 = 0;
				int limittFileInputDelimited_3 = -1;
				int lastLinetFileInputDelimited_3 = -1;

				char fieldSeparator_tFileInputDelimited_3[] = null;

				// support passing value (property: Field Separator) by 'context.fs' or
				// 'globalMap.get("fs")'.
				if (((String) ",").length() > 0) {
					fieldSeparator_tFileInputDelimited_3 = ((String) ",").toCharArray();
				} else {
					throw new IllegalArgumentException("Field Separator must be assigned a char.");
				}

				char rowSeparator_tFileInputDelimited_3[] = null;

				// support passing value (property: Row Separator) by 'context.rs' or
				// 'globalMap.get("rs")'.
				if (((String) "\n").length() > 0) {
					rowSeparator_tFileInputDelimited_3 = ((String) "\n").toCharArray();
				} else {
					throw new IllegalArgumentException("Row Separator must be assigned a char.");
				}

				Object filename_tFileInputDelimited_3 = /** Start field tFileInputDelimited_3:FILENAME */
						"C:/Users/Alix Lemoine/Documents/M2/S1/Entrepôt de Données/Projet/TP_Donnees_JO_Paris_2024/athletes.csv"/**
																																 * End
																																 * field
																																 * tFileInputDelimited_3:FILENAME
																																 */
				;
				com.talend.csv.CSVReader csvReadertFileInputDelimited_3 = null;

				try {

					String[] rowtFileInputDelimited_3 = null;
					int currentLinetFileInputDelimited_3 = 0;
					int outputLinetFileInputDelimited_3 = 0;
					try {// TD110 begin
						if (filename_tFileInputDelimited_3 instanceof java.io.InputStream) {

							int footer_value_tFileInputDelimited_3 = 0;
							if (footer_value_tFileInputDelimited_3 > 0) {
								throw new java.lang.Exception(
										"When the input source is a stream,footer shouldn't be bigger than 0.");
							}

							csvReadertFileInputDelimited_3 = new com.talend.csv.CSVReader(
									(java.io.InputStream) filename_tFileInputDelimited_3,
									fieldSeparator_tFileInputDelimited_3[0], "UTF-8");
						} else {
							csvReadertFileInputDelimited_3 = new com.talend.csv.CSVReader(
									String.valueOf(filename_tFileInputDelimited_3),
									fieldSeparator_tFileInputDelimited_3[0], "UTF-8");
						}

						csvReadertFileInputDelimited_3.setTrimWhitespace(false);
						if ((rowSeparator_tFileInputDelimited_3[0] != '\n')
								&& (rowSeparator_tFileInputDelimited_3[0] != '\r'))
							csvReadertFileInputDelimited_3.setLineEnd("" + rowSeparator_tFileInputDelimited_3[0]);

						csvReadertFileInputDelimited_3.setQuoteChar('"');

						csvReadertFileInputDelimited_3.setEscapeChar(csvReadertFileInputDelimited_3.getQuoteChar());

						if (footer_tFileInputDelimited_3 > 0) {
							for (totalLinetFileInputDelimited_3 = 0; totalLinetFileInputDelimited_3 < 1; totalLinetFileInputDelimited_3++) {
								csvReadertFileInputDelimited_3.readNext();
							}
							csvReadertFileInputDelimited_3.setSkipEmptyRecords(false);
							while (csvReadertFileInputDelimited_3.readNext()) {

								totalLinetFileInputDelimited_3++;

							}
							int lastLineTemptFileInputDelimited_3 = totalLinetFileInputDelimited_3
									- footer_tFileInputDelimited_3 < 0 ? 0
											: totalLinetFileInputDelimited_3 - footer_tFileInputDelimited_3;
							if (lastLinetFileInputDelimited_3 > 0) {
								lastLinetFileInputDelimited_3 = lastLinetFileInputDelimited_3 < lastLineTemptFileInputDelimited_3
										? lastLinetFileInputDelimited_3
										: lastLineTemptFileInputDelimited_3;
							} else {
								lastLinetFileInputDelimited_3 = lastLineTemptFileInputDelimited_3;
							}

							csvReadertFileInputDelimited_3.close();
							if (filename_tFileInputDelimited_3 instanceof java.io.InputStream) {
								csvReadertFileInputDelimited_3 = new com.talend.csv.CSVReader(
										(java.io.InputStream) filename_tFileInputDelimited_3,
										fieldSeparator_tFileInputDelimited_3[0], "UTF-8");
							} else {
								csvReadertFileInputDelimited_3 = new com.talend.csv.CSVReader(
										String.valueOf(filename_tFileInputDelimited_3),
										fieldSeparator_tFileInputDelimited_3[0], "UTF-8");
							}
							csvReadertFileInputDelimited_3.setTrimWhitespace(false);
							if ((rowSeparator_tFileInputDelimited_3[0] != '\n')
									&& (rowSeparator_tFileInputDelimited_3[0] != '\r'))
								csvReadertFileInputDelimited_3.setLineEnd("" + rowSeparator_tFileInputDelimited_3[0]);

							csvReadertFileInputDelimited_3.setQuoteChar('"');

							csvReadertFileInputDelimited_3.setEscapeChar(csvReadertFileInputDelimited_3.getQuoteChar());

						}

						if (limittFileInputDelimited_3 != 0) {
							for (currentLinetFileInputDelimited_3 = 0; currentLinetFileInputDelimited_3 < 1; currentLinetFileInputDelimited_3++) {
								csvReadertFileInputDelimited_3.readNext();
							}
						}
						csvReadertFileInputDelimited_3.setSkipEmptyRecords(false);

					} catch (java.lang.Exception e) {
						globalMap.put("tFileInputDelimited_3_ERROR_MESSAGE", e.getMessage());

						System.err.println(e.getMessage());

					} // TD110 end

					while (limittFileInputDelimited_3 != 0 && csvReadertFileInputDelimited_3 != null
							&& csvReadertFileInputDelimited_3.readNext()) {
						rowstate_tFileInputDelimited_3.reset();

						rowtFileInputDelimited_3 = csvReadertFileInputDelimited_3.getValues();

						currentLinetFileInputDelimited_3++;

						if (lastLinetFileInputDelimited_3 > -1
								&& currentLinetFileInputDelimited_3 > lastLinetFileInputDelimited_3) {
							break;
						}
						outputLinetFileInputDelimited_3++;
						if (limittFileInputDelimited_3 > 0
								&& outputLinetFileInputDelimited_3 > limittFileInputDelimited_3) {
							break;
						}

						row4 = null;

						boolean whetherReject_tFileInputDelimited_3 = false;
						row4 = new row4Struct();
						try {

							char fieldSeparator_tFileInputDelimited_3_ListType[] = null;
							// support passing value (property: Field Separator) by 'context.fs' or
							// 'globalMap.get("fs")'.
							if (((String) ",").length() > 0) {
								fieldSeparator_tFileInputDelimited_3_ListType = ((String) ",").toCharArray();
							} else {
								throw new IllegalArgumentException("Field Separator must be assigned a char.");
							}
							if (rowtFileInputDelimited_3.length == 1 && ("\015").equals(rowtFileInputDelimited_3[0])) {// empty
																														// line
																														// when
																														// row
																														// separator
																														// is
																														// '\n'

								row4.code = null;

								row4.current = null;

								row4.name = null;

								row4.name_short = null;

								row4.name_tv = null;

								row4.gender = null;

								row4.function = null;

								row4.country_code = null;

								row4.country = null;

								row4.country_long = null;

								row4.nationality = null;

								row4.nationality_long = null;

								row4.nationality_code = null;

								row4.height = null;

								row4.weight = null;

								row4.disciplines = null;

								row4.events = null;

								row4.birth_date = null;

								row4.birth_place = null;

								row4.birth_country = null;

								row4.residence_place = null;

								row4.residence_country = null;

								row4.nickname = null;

								row4.hobbies = null;

								row4.occupation = null;

								row4.education = null;

								row4.family = null;

								row4.lang = null;

								row4.coach = null;

								row4.reason = null;

								row4.hero = null;

								row4.influence = null;

								row4.philosophy = null;

								row4.sporting_relatives = null;

								row4.ritual = null;

								row4.other_sports = null;

							} else {

								int columnIndexWithD_tFileInputDelimited_3 = 0; // Column Index

								columnIndexWithD_tFileInputDelimited_3 = 0;

								if (columnIndexWithD_tFileInputDelimited_3 < rowtFileInputDelimited_3.length) {

									if (rowtFileInputDelimited_3[columnIndexWithD_tFileInputDelimited_3].length() > 0) {
										try {

											row4.code = ParserUtils.parseTo_Integer(
													rowtFileInputDelimited_3[columnIndexWithD_tFileInputDelimited_3]);

										} catch (java.lang.Exception ex_tFileInputDelimited_3) {
											globalMap.put("tFileInputDelimited_3_ERROR_MESSAGE",
													ex_tFileInputDelimited_3.getMessage());
											rowstate_tFileInputDelimited_3.setException(new RuntimeException(String
													.format("Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
															"code", "row4",
															rowtFileInputDelimited_3[columnIndexWithD_tFileInputDelimited_3],
															ex_tFileInputDelimited_3),
													ex_tFileInputDelimited_3));
										}
									} else {

										row4.code = null;

									}

								} else {

									row4.code = null;

								}

								columnIndexWithD_tFileInputDelimited_3 = 1;

								if (columnIndexWithD_tFileInputDelimited_3 < rowtFileInputDelimited_3.length) {

									row4.current = rowtFileInputDelimited_3[columnIndexWithD_tFileInputDelimited_3];

								} else {

									row4.current = null;

								}

								columnIndexWithD_tFileInputDelimited_3 = 2;

								if (columnIndexWithD_tFileInputDelimited_3 < rowtFileInputDelimited_3.length) {

									row4.name = rowtFileInputDelimited_3[columnIndexWithD_tFileInputDelimited_3];

								} else {

									row4.name = null;

								}

								columnIndexWithD_tFileInputDelimited_3 = 3;

								if (columnIndexWithD_tFileInputDelimited_3 < rowtFileInputDelimited_3.length) {

									row4.name_short = rowtFileInputDelimited_3[columnIndexWithD_tFileInputDelimited_3];

								} else {

									row4.name_short = null;

								}

								columnIndexWithD_tFileInputDelimited_3 = 4;

								if (columnIndexWithD_tFileInputDelimited_3 < rowtFileInputDelimited_3.length) {

									row4.name_tv = rowtFileInputDelimited_3[columnIndexWithD_tFileInputDelimited_3];

								} else {

									row4.name_tv = null;

								}

								columnIndexWithD_tFileInputDelimited_3 = 5;

								if (columnIndexWithD_tFileInputDelimited_3 < rowtFileInputDelimited_3.length) {

									row4.gender = rowtFileInputDelimited_3[columnIndexWithD_tFileInputDelimited_3];

								} else {

									row4.gender = null;

								}

								columnIndexWithD_tFileInputDelimited_3 = 6;

								if (columnIndexWithD_tFileInputDelimited_3 < rowtFileInputDelimited_3.length) {

									row4.function = rowtFileInputDelimited_3[columnIndexWithD_tFileInputDelimited_3];

								} else {

									row4.function = null;

								}

								columnIndexWithD_tFileInputDelimited_3 = 7;

								if (columnIndexWithD_tFileInputDelimited_3 < rowtFileInputDelimited_3.length) {

									row4.country_code = rowtFileInputDelimited_3[columnIndexWithD_tFileInputDelimited_3];

								} else {

									row4.country_code = null;

								}

								columnIndexWithD_tFileInputDelimited_3 = 8;

								if (columnIndexWithD_tFileInputDelimited_3 < rowtFileInputDelimited_3.length) {

									row4.country = rowtFileInputDelimited_3[columnIndexWithD_tFileInputDelimited_3];

								} else {

									row4.country = null;

								}

								columnIndexWithD_tFileInputDelimited_3 = 9;

								if (columnIndexWithD_tFileInputDelimited_3 < rowtFileInputDelimited_3.length) {

									row4.country_long = rowtFileInputDelimited_3[columnIndexWithD_tFileInputDelimited_3];

								} else {

									row4.country_long = null;

								}

								columnIndexWithD_tFileInputDelimited_3 = 10;

								if (columnIndexWithD_tFileInputDelimited_3 < rowtFileInputDelimited_3.length) {

									row4.nationality = rowtFileInputDelimited_3[columnIndexWithD_tFileInputDelimited_3];

								} else {

									row4.nationality = null;

								}

								columnIndexWithD_tFileInputDelimited_3 = 11;

								if (columnIndexWithD_tFileInputDelimited_3 < rowtFileInputDelimited_3.length) {

									row4.nationality_long = rowtFileInputDelimited_3[columnIndexWithD_tFileInputDelimited_3];

								} else {

									row4.nationality_long = null;

								}

								columnIndexWithD_tFileInputDelimited_3 = 12;

								if (columnIndexWithD_tFileInputDelimited_3 < rowtFileInputDelimited_3.length) {

									row4.nationality_code = rowtFileInputDelimited_3[columnIndexWithD_tFileInputDelimited_3];

								} else {

									row4.nationality_code = null;

								}

								columnIndexWithD_tFileInputDelimited_3 = 13;

								if (columnIndexWithD_tFileInputDelimited_3 < rowtFileInputDelimited_3.length) {

									if (rowtFileInputDelimited_3[columnIndexWithD_tFileInputDelimited_3].length() > 0) {
										try {

											row4.height = ParserUtils.parseTo_Float(
													rowtFileInputDelimited_3[columnIndexWithD_tFileInputDelimited_3]);

										} catch (java.lang.Exception ex_tFileInputDelimited_3) {
											globalMap.put("tFileInputDelimited_3_ERROR_MESSAGE",
													ex_tFileInputDelimited_3.getMessage());
											rowstate_tFileInputDelimited_3.setException(new RuntimeException(String
													.format("Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
															"height", "row4",
															rowtFileInputDelimited_3[columnIndexWithD_tFileInputDelimited_3],
															ex_tFileInputDelimited_3),
													ex_tFileInputDelimited_3));
										}
									} else {

										row4.height = null;

									}

								} else {

									row4.height = null;

								}

								columnIndexWithD_tFileInputDelimited_3 = 14;

								if (columnIndexWithD_tFileInputDelimited_3 < rowtFileInputDelimited_3.length) {

									if (rowtFileInputDelimited_3[columnIndexWithD_tFileInputDelimited_3].length() > 0) {
										try {

											row4.weight = ParserUtils.parseTo_Float(
													rowtFileInputDelimited_3[columnIndexWithD_tFileInputDelimited_3]);

										} catch (java.lang.Exception ex_tFileInputDelimited_3) {
											globalMap.put("tFileInputDelimited_3_ERROR_MESSAGE",
													ex_tFileInputDelimited_3.getMessage());
											rowstate_tFileInputDelimited_3.setException(new RuntimeException(String
													.format("Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
															"weight", "row4",
															rowtFileInputDelimited_3[columnIndexWithD_tFileInputDelimited_3],
															ex_tFileInputDelimited_3),
													ex_tFileInputDelimited_3));
										}
									} else {

										row4.weight = null;

									}

								} else {

									row4.weight = null;

								}

								columnIndexWithD_tFileInputDelimited_3 = 15;

								if (columnIndexWithD_tFileInputDelimited_3 < rowtFileInputDelimited_3.length) {

									row4.disciplines = rowtFileInputDelimited_3[columnIndexWithD_tFileInputDelimited_3];

								} else {

									row4.disciplines = null;

								}

								columnIndexWithD_tFileInputDelimited_3 = 16;

								if (columnIndexWithD_tFileInputDelimited_3 < rowtFileInputDelimited_3.length) {

									row4.events = rowtFileInputDelimited_3[columnIndexWithD_tFileInputDelimited_3];

								} else {

									row4.events = null;

								}

								columnIndexWithD_tFileInputDelimited_3 = 17;

								if (columnIndexWithD_tFileInputDelimited_3 < rowtFileInputDelimited_3.length) {

									if (rowtFileInputDelimited_3[columnIndexWithD_tFileInputDelimited_3].length() > 0) {
										try {

											row4.birth_date = ParserUtils.parseTo_Date(
													rowtFileInputDelimited_3[columnIndexWithD_tFileInputDelimited_3],
													"yyyy-mm-dd");

										} catch (java.lang.Exception ex_tFileInputDelimited_3) {
											globalMap.put("tFileInputDelimited_3_ERROR_MESSAGE",
													ex_tFileInputDelimited_3.getMessage());
											rowstate_tFileInputDelimited_3.setException(new RuntimeException(String
													.format("Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
															"birth_date", "row4",
															rowtFileInputDelimited_3[columnIndexWithD_tFileInputDelimited_3],
															ex_tFileInputDelimited_3),
													ex_tFileInputDelimited_3));
										}
									} else {

										row4.birth_date = null;

									}

								} else {

									row4.birth_date = null;

								}

								columnIndexWithD_tFileInputDelimited_3 = 18;

								if (columnIndexWithD_tFileInputDelimited_3 < rowtFileInputDelimited_3.length) {

									row4.birth_place = rowtFileInputDelimited_3[columnIndexWithD_tFileInputDelimited_3];

								} else {

									row4.birth_place = null;

								}

								columnIndexWithD_tFileInputDelimited_3 = 19;

								if (columnIndexWithD_tFileInputDelimited_3 < rowtFileInputDelimited_3.length) {

									row4.birth_country = rowtFileInputDelimited_3[columnIndexWithD_tFileInputDelimited_3];

								} else {

									row4.birth_country = null;

								}

								columnIndexWithD_tFileInputDelimited_3 = 20;

								if (columnIndexWithD_tFileInputDelimited_3 < rowtFileInputDelimited_3.length) {

									row4.residence_place = rowtFileInputDelimited_3[columnIndexWithD_tFileInputDelimited_3];

								} else {

									row4.residence_place = null;

								}

								columnIndexWithD_tFileInputDelimited_3 = 21;

								if (columnIndexWithD_tFileInputDelimited_3 < rowtFileInputDelimited_3.length) {

									row4.residence_country = rowtFileInputDelimited_3[columnIndexWithD_tFileInputDelimited_3];

								} else {

									row4.residence_country = null;

								}

								columnIndexWithD_tFileInputDelimited_3 = 22;

								if (columnIndexWithD_tFileInputDelimited_3 < rowtFileInputDelimited_3.length) {

									row4.nickname = rowtFileInputDelimited_3[columnIndexWithD_tFileInputDelimited_3];

								} else {

									row4.nickname = null;

								}

								columnIndexWithD_tFileInputDelimited_3 = 23;

								if (columnIndexWithD_tFileInputDelimited_3 < rowtFileInputDelimited_3.length) {

									row4.hobbies = rowtFileInputDelimited_3[columnIndexWithD_tFileInputDelimited_3];

								} else {

									row4.hobbies = null;

								}

								columnIndexWithD_tFileInputDelimited_3 = 24;

								if (columnIndexWithD_tFileInputDelimited_3 < rowtFileInputDelimited_3.length) {

									row4.occupation = rowtFileInputDelimited_3[columnIndexWithD_tFileInputDelimited_3];

								} else {

									row4.occupation = null;

								}

								columnIndexWithD_tFileInputDelimited_3 = 25;

								if (columnIndexWithD_tFileInputDelimited_3 < rowtFileInputDelimited_3.length) {

									row4.education = rowtFileInputDelimited_3[columnIndexWithD_tFileInputDelimited_3];

								} else {

									row4.education = null;

								}

								columnIndexWithD_tFileInputDelimited_3 = 26;

								if (columnIndexWithD_tFileInputDelimited_3 < rowtFileInputDelimited_3.length) {

									row4.family = rowtFileInputDelimited_3[columnIndexWithD_tFileInputDelimited_3];

								} else {

									row4.family = null;

								}

								columnIndexWithD_tFileInputDelimited_3 = 27;

								if (columnIndexWithD_tFileInputDelimited_3 < rowtFileInputDelimited_3.length) {

									row4.lang = rowtFileInputDelimited_3[columnIndexWithD_tFileInputDelimited_3];

								} else {

									row4.lang = null;

								}

								columnIndexWithD_tFileInputDelimited_3 = 28;

								if (columnIndexWithD_tFileInputDelimited_3 < rowtFileInputDelimited_3.length) {

									row4.coach = rowtFileInputDelimited_3[columnIndexWithD_tFileInputDelimited_3];

								} else {

									row4.coach = null;

								}

								columnIndexWithD_tFileInputDelimited_3 = 29;

								if (columnIndexWithD_tFileInputDelimited_3 < rowtFileInputDelimited_3.length) {

									row4.reason = rowtFileInputDelimited_3[columnIndexWithD_tFileInputDelimited_3];

								} else {

									row4.reason = null;

								}

								columnIndexWithD_tFileInputDelimited_3 = 30;

								if (columnIndexWithD_tFileInputDelimited_3 < rowtFileInputDelimited_3.length) {

									row4.hero = rowtFileInputDelimited_3[columnIndexWithD_tFileInputDelimited_3];

								} else {

									row4.hero = null;

								}

								columnIndexWithD_tFileInputDelimited_3 = 31;

								if (columnIndexWithD_tFileInputDelimited_3 < rowtFileInputDelimited_3.length) {

									row4.influence = rowtFileInputDelimited_3[columnIndexWithD_tFileInputDelimited_3];

								} else {

									row4.influence = null;

								}

								columnIndexWithD_tFileInputDelimited_3 = 32;

								if (columnIndexWithD_tFileInputDelimited_3 < rowtFileInputDelimited_3.length) {

									row4.philosophy = rowtFileInputDelimited_3[columnIndexWithD_tFileInputDelimited_3];

								} else {

									row4.philosophy = null;

								}

								columnIndexWithD_tFileInputDelimited_3 = 33;

								if (columnIndexWithD_tFileInputDelimited_3 < rowtFileInputDelimited_3.length) {

									row4.sporting_relatives = rowtFileInputDelimited_3[columnIndexWithD_tFileInputDelimited_3];

								} else {

									row4.sporting_relatives = null;

								}

								columnIndexWithD_tFileInputDelimited_3 = 34;

								if (columnIndexWithD_tFileInputDelimited_3 < rowtFileInputDelimited_3.length) {

									row4.ritual = rowtFileInputDelimited_3[columnIndexWithD_tFileInputDelimited_3];

								} else {

									row4.ritual = null;

								}

								columnIndexWithD_tFileInputDelimited_3 = 35;

								if (columnIndexWithD_tFileInputDelimited_3 < rowtFileInputDelimited_3.length) {

									row4.other_sports = rowtFileInputDelimited_3[columnIndexWithD_tFileInputDelimited_3];

								} else {

									row4.other_sports = null;

								}

							}

							if (rowstate_tFileInputDelimited_3.getException() != null) {
								throw rowstate_tFileInputDelimited_3.getException();
							}

						} catch (java.lang.Exception e) {
							globalMap.put("tFileInputDelimited_3_ERROR_MESSAGE", e.getMessage());
							whetherReject_tFileInputDelimited_3 = true;

							System.err.println(e.getMessage());
							row4 = null;

							globalMap.put("tFileInputDelimited_3_ERROR_MESSAGE", e.getMessage());

						}

						/**
						 * [tFileInputDelimited_3 begin ] stop
						 */

						/**
						 * [tFileInputDelimited_3 main ] start
						 */

						currentComponent = "tFileInputDelimited_3";

						tos_count_tFileInputDelimited_3++;

						/**
						 * [tFileInputDelimited_3 main ] stop
						 */

						/**
						 * [tFileInputDelimited_3 process_data_begin ] start
						 */

						currentComponent = "tFileInputDelimited_3";

						/**
						 * [tFileInputDelimited_3 process_data_begin ] stop
						 */
// Start of branch "row4"
						if (row4 != null) {

							/**
							 * [tMap_1 main ] start
							 */

							currentComponent = "tMap_1";

							if (execStat) {
								runStat.updateStatOnConnection(iterateId, 1, 1

										, "row4"

								);
							}

							boolean hasCasePrimitiveKeyWithNull_tMap_1 = false;

							// ###############################
							// # Input tables (lookups)
							boolean rejectedInnerJoin_tMap_1 = false;
							boolean mainRowRejected_tMap_1 = false;

							///////////////////////////////////////////////
							// Starting Lookup Table "row5"
							///////////////////////////////////////////////

							boolean forceLooprow5 = false;

							row5Struct row5ObjectFromLookup = null;

							if (!rejectedInnerJoin_tMap_1) { // G_TM_M_020

								hasCasePrimitiveKeyWithNull_tMap_1 = false;

								row5HashKey.code = row4.country_code;

								row5HashKey.hashCodeDirty = true;

								tHash_Lookup_row5.lookup(row5HashKey);

							} // G_TM_M_020

							if (tHash_Lookup_row5 != null && tHash_Lookup_row5.getCount(row5HashKey) > 1) { // G 071

								// System.out.println("WARNING: UNIQUE MATCH is configured for the lookup 'row5'
								// and it contains more one result from keys : row5.code = '" + row5HashKey.code
								// + "'");
							} // G 071

							row5Struct row5 = null;

							row5Struct fromLookup_row5 = null;
							row5 = row5Default;

							if (tHash_Lookup_row5 != null && tHash_Lookup_row5.hasNext()) { // G 099

								fromLookup_row5 = tHash_Lookup_row5.next();

							} // G 099

							if (fromLookup_row5 != null) {
								row5 = fromLookup_row5;
							}

							// ###############################
							{ // start of Var scope

								// ###############################
								// # Vars tables

								Var__tMap_1__Struct Var = Var__tMap_1;// ###############################
								// ###############################
								// # Output tables

								out1 = null;
								out2 = null;

// # Output table : 'out1'
// # Filter conditions 
								if (

								row5.code != null

								) {
									out1_tmp.code_athlete = row4.code;
									out1_tmp.code_pays = row5.code;
									out1 = out1_tmp;
								} // closing filter/reject

// # Output table : 'out2'
// # Filter conditions 
								if (

								row5.code == null

								) {
									out2_tmp.code_athlete = row4.code;
									out2_tmp.code_pays = row5.code;
									out2 = out2_tmp;
								} // closing filter/reject
// ###############################

							} // end of Var scope

							rejectedInnerJoin_tMap_1 = false;

							tos_count_tMap_1++;

							/**
							 * [tMap_1 main ] stop
							 */

							/**
							 * [tMap_1 process_data_begin ] start
							 */

							currentComponent = "tMap_1";

							/**
							 * [tMap_1 process_data_begin ] stop
							 */
// Start of branch "out1"
							if (out1 != null) {

								/**
								 * [tLogRow_3 main ] start
								 */

								currentComponent = "tLogRow_3";

								if (execStat) {
									runStat.updateStatOnConnection(iterateId, 1, 1

											, "out1"

									);
								}

///////////////////////		

								strBuffer_tLogRow_3 = new StringBuilder();

								strBuffer_tLogRow_3.append(String.valueOf(out1.code_athlete));

								strBuffer_tLogRow_3.append("|");

								if (out1.code_pays != null) { //

									strBuffer_tLogRow_3.append(String.valueOf(out1.code_pays));

								} //

								if (globalMap.get("tLogRow_CONSOLE") != null) {
									consoleOut_tLogRow_3 = (java.io.PrintStream) globalMap.get("tLogRow_CONSOLE");
								} else {
									consoleOut_tLogRow_3 = new java.io.PrintStream(
											new java.io.BufferedOutputStream(System.out));
									globalMap.put("tLogRow_CONSOLE", consoleOut_tLogRow_3);
								}
								consoleOut_tLogRow_3.println(strBuffer_tLogRow_3.toString());
								consoleOut_tLogRow_3.flush();
								nb_line_tLogRow_3++;
//////

//////                    

///////////////////////    			

								tos_count_tLogRow_3++;

								/**
								 * [tLogRow_3 main ] stop
								 */

								/**
								 * [tLogRow_3 process_data_begin ] start
								 */

								currentComponent = "tLogRow_3";

								/**
								 * [tLogRow_3 process_data_begin ] stop
								 */

								/**
								 * [tLogRow_3 process_data_end ] start
								 */

								currentComponent = "tLogRow_3";

								/**
								 * [tLogRow_3 process_data_end ] stop
								 */

							} // End of branch "out1"

// Start of branch "out2"
							if (out2 != null) {

								/**
								 * [tLogRow_4 main ] start
								 */

								currentComponent = "tLogRow_4";

								if (execStat) {
									runStat.updateStatOnConnection(iterateId, 1, 1

											, "out2"

									);
								}

///////////////////////		

								strBuffer_tLogRow_4 = new StringBuilder();

								if (out2.code_athlete != null) { //

									strBuffer_tLogRow_4.append(String.valueOf(out2.code_athlete));

								} //

								strBuffer_tLogRow_4.append("|");

								if (out2.code_pays != null) { //

									strBuffer_tLogRow_4.append(String.valueOf(out2.code_pays));

								} //

								if (globalMap.get("tLogRow_CONSOLE") != null) {
									consoleOut_tLogRow_4 = (java.io.PrintStream) globalMap.get("tLogRow_CONSOLE");
								} else {
									consoleOut_tLogRow_4 = new java.io.PrintStream(
											new java.io.BufferedOutputStream(System.out));
									globalMap.put("tLogRow_CONSOLE", consoleOut_tLogRow_4);
								}
								consoleOut_tLogRow_4.println(strBuffer_tLogRow_4.toString());
								consoleOut_tLogRow_4.flush();
								nb_line_tLogRow_4++;
//////

//////                    

///////////////////////    			

								tos_count_tLogRow_4++;

								/**
								 * [tLogRow_4 main ] stop
								 */

								/**
								 * [tLogRow_4 process_data_begin ] start
								 */

								currentComponent = "tLogRow_4";

								/**
								 * [tLogRow_4 process_data_begin ] stop
								 */

								/**
								 * [tLogRow_4 process_data_end ] start
								 */

								currentComponent = "tLogRow_4";

								/**
								 * [tLogRow_4 process_data_end ] stop
								 */

							} // End of branch "out2"

							/**
							 * [tMap_1 process_data_end ] start
							 */

							currentComponent = "tMap_1";

							/**
							 * [tMap_1 process_data_end ] stop
							 */

						} // End of branch "row4"

						/**
						 * [tFileInputDelimited_3 process_data_end ] start
						 */

						currentComponent = "tFileInputDelimited_3";

						/**
						 * [tFileInputDelimited_3 process_data_end ] stop
						 */

						/**
						 * [tFileInputDelimited_3 end ] start
						 */

						currentComponent = "tFileInputDelimited_3";

						nb_line_tFileInputDelimited_3++;
					}

				} finally {
					if (!(filename_tFileInputDelimited_3 instanceof java.io.InputStream)) {
						if (csvReadertFileInputDelimited_3 != null) {
							csvReadertFileInputDelimited_3.close();
						}
					}
					if (csvReadertFileInputDelimited_3 != null) {
						globalMap.put("tFileInputDelimited_3_NB_LINE", nb_line_tFileInputDelimited_3);
					}

				}

				ok_Hash.put("tFileInputDelimited_3", true);
				end_Hash.put("tFileInputDelimited_3", System.currentTimeMillis());

				/**
				 * [tFileInputDelimited_3 end ] stop
				 */

				/**
				 * [tMap_1 end ] start
				 */

				currentComponent = "tMap_1";

// ###############################
// # Lookup hashes releasing
				if (tHash_Lookup_row5 != null) {
					tHash_Lookup_row5.endGet();
				}
				globalMap.remove("tHash_Lookup_row5");

// ###############################      

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row4");
				}

				ok_Hash.put("tMap_1", true);
				end_Hash.put("tMap_1", System.currentTimeMillis());

				/**
				 * [tMap_1 end ] stop
				 */

				/**
				 * [tLogRow_3 end ] start
				 */

				currentComponent = "tLogRow_3";

//////
//////
				globalMap.put("tLogRow_3_NB_LINE", nb_line_tLogRow_3);

///////////////////////    			

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "out1");
				}

				ok_Hash.put("tLogRow_3", true);
				end_Hash.put("tLogRow_3", System.currentTimeMillis());

				/**
				 * [tLogRow_3 end ] stop
				 */

				/**
				 * [tLogRow_4 end ] start
				 */

				currentComponent = "tLogRow_4";

//////
//////
				globalMap.put("tLogRow_4_NB_LINE", nb_line_tLogRow_4);

///////////////////////    			

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "out2");
				}

				ok_Hash.put("tLogRow_4", true);
				end_Hash.put("tLogRow_4", System.currentTimeMillis());

				/**
				 * [tLogRow_4 end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			// free memory for "tMap_1"
			globalMap.remove("tHash_Lookup_row5");

			try {

				/**
				 * [tFileInputDelimited_3 finally ] start
				 */

				currentComponent = "tFileInputDelimited_3";

				/**
				 * [tFileInputDelimited_3 finally ] stop
				 */

				/**
				 * [tMap_1 finally ] start
				 */

				currentComponent = "tMap_1";

				/**
				 * [tMap_1 finally ] stop
				 */

				/**
				 * [tLogRow_3 finally ] start
				 */

				currentComponent = "tLogRow_3";

				/**
				 * [tLogRow_3 finally ] stop
				 */

				/**
				 * [tLogRow_4 finally ] start
				 */

				currentComponent = "tLogRow_4";

				/**
				 * [tLogRow_4 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tFileInputDelimited_3_SUBPROCESS_STATE", 1);
	}

	public static class row5Struct implements routines.system.IPersistableComparableLookupRow<row5Struct> {
		final static byte[] commonByteArrayLock_PROJETTALEND1_completude_nocs = new byte[0];
		static byte[] commonByteArray_PROJETTALEND1_completude_nocs = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public String code;

		public String getCode() {
			return this.code;
		}

		public String country;

		public String getCountry() {
			return this.country;
		}

		public String country_long;

		public String getCountry_long() {
			return this.country_long;
		}

		public String tag;

		public String getTag() {
			return this.tag;
		}

		public String note;

		public String getNote() {
			return this.note;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.code == null) ? 0 : this.code.hashCode());

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final row5Struct other = (row5Struct) obj;

			if (this.code == null) {
				if (other.code != null)
					return false;

			} else if (!this.code.equals(other.code))

				return false;

			return true;
		}

		public void copyDataTo(row5Struct other) {

			other.code = this.code;
			other.country = this.country;
			other.country_long = this.country_long;
			other.tag = this.tag;
			other.note = this.note;

		}

		public void copyKeysDataTo(row5Struct other) {

			other.code = this.code;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PROJETTALEND1_completude_nocs.length) {
					if (length < 1024 && commonByteArray_PROJETTALEND1_completude_nocs.length == 0) {
						commonByteArray_PROJETTALEND1_completude_nocs = new byte[1024];
					} else {
						commonByteArray_PROJETTALEND1_completude_nocs = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_PROJETTALEND1_completude_nocs, 0, length);
				strReturn = new String(commonByteArray_PROJETTALEND1_completude_nocs, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PROJETTALEND1_completude_nocs.length) {
					if (length < 1024 && commonByteArray_PROJETTALEND1_completude_nocs.length == 0) {
						commonByteArray_PROJETTALEND1_completude_nocs = new byte[1024];
					} else {
						commonByteArray_PROJETTALEND1_completude_nocs = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_PROJETTALEND1_completude_nocs, 0, length);
				strReturn = new String(commonByteArray_PROJETTALEND1_completude_nocs, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		private String readString(DataInputStream dis, ObjectInputStream ois) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				byte[] byteArray = new byte[length];
				dis.read(byteArray);
				strReturn = new String(byteArray, utf8Charset);
			}
			return strReturn;
		}

		private String readString(DataInputStream dis, org.jboss.marshalling.Unmarshaller unmarshaller)
				throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				byte[] byteArray = new byte[length];
				unmarshaller.read(byteArray);
				strReturn = new String(byteArray, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, DataOutputStream dos, org.jboss.marshalling.Marshaller marshaller)
				throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		private void writeString(String str, DataOutputStream dos, ObjectOutputStream oos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		public void readKeysData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_PROJETTALEND1_completude_nocs) {

				try {

					int length = 0;

					this.code = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_PROJETTALEND1_completude_nocs) {

				try {

					int length = 0;

					this.code = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeKeysData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.code, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeKeysData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.code, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		/**
		 * Fill Values data by reading ObjectInputStream.
		 */
		public void readValuesData(DataInputStream dis, ObjectInputStream ois) {
			try {

				int length = 0;

				this.country = readString(dis, ois);

				this.country_long = readString(dis, ois);

				this.tag = readString(dis, ois);

				this.note = readString(dis, ois);

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		public void readValuesData(DataInputStream dis, org.jboss.marshalling.Unmarshaller objectIn) {
			try {
				int length = 0;

				this.country = readString(dis, objectIn);

				this.country_long = readString(dis, objectIn);

				this.tag = readString(dis, objectIn);

				this.note = readString(dis, objectIn);

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		/**
		 * Return a byte array which represents Values data.
		 */
		public void writeValuesData(DataOutputStream dos, ObjectOutputStream oos) {
			try {

				writeString(this.country, dos, oos);

				writeString(this.country_long, dos, oos);

				writeString(this.tag, dos, oos);

				writeString(this.note, dos, oos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeValuesData(DataOutputStream dos, org.jboss.marshalling.Marshaller objectOut) {
			try {

				writeString(this.country, dos, objectOut);

				writeString(this.country_long, dos, objectOut);

				writeString(this.tag, dos, objectOut);

				writeString(this.note, dos, objectOut);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		public boolean supportMarshaller() {
			return true;
		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("code=" + code);
			sb.append(",country=" + country);
			sb.append(",country_long=" + country_long);
			sb.append(",tag=" + tag);
			sb.append(",note=" + note);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row5Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.code, other.code);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tFileInputDelimited_2Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tFileInputDelimited_2_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";

		String currentComponent = "";
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				row5Struct row5 = new row5Struct();

				/**
				 * [tAdvancedHash_row5 begin ] start
				 */

				ok_Hash.put("tAdvancedHash_row5", false);
				start_Hash.put("tAdvancedHash_row5", System.currentTimeMillis());

				currentComponent = "tAdvancedHash_row5";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row5");
				}

				int tos_count_tAdvancedHash_row5 = 0;

				// connection name:row5
				// source node:tFileInputDelimited_2 - inputs:(after_tFileInputDelimited_3)
				// outputs:(row5,row5) | target node:tAdvancedHash_row5 - inputs:(row5)
				// outputs:()
				// linked node: tMap_1 - inputs:(row4,row5) outputs:(out1,out2)

				org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_row5 = org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.UNIQUE_MATCH;

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row5Struct> tHash_Lookup_row5 = org.talend.designer.components.lookup.memory.AdvancedMemoryLookup
						.<row5Struct>getLookup(matchingModeEnum_row5);

				globalMap.put("tHash_Lookup_row5", tHash_Lookup_row5);

				/**
				 * [tAdvancedHash_row5 begin ] stop
				 */

				/**
				 * [tFileInputDelimited_2 begin ] start
				 */

				ok_Hash.put("tFileInputDelimited_2", false);
				start_Hash.put("tFileInputDelimited_2", System.currentTimeMillis());

				currentComponent = "tFileInputDelimited_2";

				int tos_count_tFileInputDelimited_2 = 0;

				final routines.system.RowState rowstate_tFileInputDelimited_2 = new routines.system.RowState();

				int nb_line_tFileInputDelimited_2 = 0;
				int footer_tFileInputDelimited_2 = 0;
				int totalLinetFileInputDelimited_2 = 0;
				int limittFileInputDelimited_2 = -1;
				int lastLinetFileInputDelimited_2 = -1;

				char fieldSeparator_tFileInputDelimited_2[] = null;

				// support passing value (property: Field Separator) by 'context.fs' or
				// 'globalMap.get("fs")'.
				if (((String) ",").length() > 0) {
					fieldSeparator_tFileInputDelimited_2 = ((String) ",").toCharArray();
				} else {
					throw new IllegalArgumentException("Field Separator must be assigned a char.");
				}

				char rowSeparator_tFileInputDelimited_2[] = null;

				// support passing value (property: Row Separator) by 'context.rs' or
				// 'globalMap.get("rs")'.
				if (((String) "\n").length() > 0) {
					rowSeparator_tFileInputDelimited_2 = ((String) "\n").toCharArray();
				} else {
					throw new IllegalArgumentException("Row Separator must be assigned a char.");
				}

				Object filename_tFileInputDelimited_2 = /** Start field tFileInputDelimited_2:FILENAME */
						"C:/Users/Alix Lemoine/Documents/M2/S1/Entrepôt de Données/Projet/TP_Donnees_JO_Paris_2024/nocs.csv"/**
																															 * End
																															 * field
																															 * tFileInputDelimited_2:FILENAME
																															 */
				;
				com.talend.csv.CSVReader csvReadertFileInputDelimited_2 = null;

				try {

					String[] rowtFileInputDelimited_2 = null;
					int currentLinetFileInputDelimited_2 = 0;
					int outputLinetFileInputDelimited_2 = 0;
					try {// TD110 begin
						if (filename_tFileInputDelimited_2 instanceof java.io.InputStream) {

							int footer_value_tFileInputDelimited_2 = 0;
							if (footer_value_tFileInputDelimited_2 > 0) {
								throw new java.lang.Exception(
										"When the input source is a stream,footer shouldn't be bigger than 0.");
							}

							csvReadertFileInputDelimited_2 = new com.talend.csv.CSVReader(
									(java.io.InputStream) filename_tFileInputDelimited_2,
									fieldSeparator_tFileInputDelimited_2[0], "UTF-8");
						} else {
							csvReadertFileInputDelimited_2 = new com.talend.csv.CSVReader(
									String.valueOf(filename_tFileInputDelimited_2),
									fieldSeparator_tFileInputDelimited_2[0], "UTF-8");
						}

						csvReadertFileInputDelimited_2.setTrimWhitespace(false);
						if ((rowSeparator_tFileInputDelimited_2[0] != '\n')
								&& (rowSeparator_tFileInputDelimited_2[0] != '\r'))
							csvReadertFileInputDelimited_2.setLineEnd("" + rowSeparator_tFileInputDelimited_2[0]);

						csvReadertFileInputDelimited_2.setQuoteChar('"');

						csvReadertFileInputDelimited_2.setEscapeChar(csvReadertFileInputDelimited_2.getQuoteChar());

						if (footer_tFileInputDelimited_2 > 0) {
							for (totalLinetFileInputDelimited_2 = 0; totalLinetFileInputDelimited_2 < 1; totalLinetFileInputDelimited_2++) {
								csvReadertFileInputDelimited_2.readNext();
							}
							csvReadertFileInputDelimited_2.setSkipEmptyRecords(false);
							while (csvReadertFileInputDelimited_2.readNext()) {

								totalLinetFileInputDelimited_2++;

							}
							int lastLineTemptFileInputDelimited_2 = totalLinetFileInputDelimited_2
									- footer_tFileInputDelimited_2 < 0 ? 0
											: totalLinetFileInputDelimited_2 - footer_tFileInputDelimited_2;
							if (lastLinetFileInputDelimited_2 > 0) {
								lastLinetFileInputDelimited_2 = lastLinetFileInputDelimited_2 < lastLineTemptFileInputDelimited_2
										? lastLinetFileInputDelimited_2
										: lastLineTemptFileInputDelimited_2;
							} else {
								lastLinetFileInputDelimited_2 = lastLineTemptFileInputDelimited_2;
							}

							csvReadertFileInputDelimited_2.close();
							if (filename_tFileInputDelimited_2 instanceof java.io.InputStream) {
								csvReadertFileInputDelimited_2 = new com.talend.csv.CSVReader(
										(java.io.InputStream) filename_tFileInputDelimited_2,
										fieldSeparator_tFileInputDelimited_2[0], "UTF-8");
							} else {
								csvReadertFileInputDelimited_2 = new com.talend.csv.CSVReader(
										String.valueOf(filename_tFileInputDelimited_2),
										fieldSeparator_tFileInputDelimited_2[0], "UTF-8");
							}
							csvReadertFileInputDelimited_2.setTrimWhitespace(false);
							if ((rowSeparator_tFileInputDelimited_2[0] != '\n')
									&& (rowSeparator_tFileInputDelimited_2[0] != '\r'))
								csvReadertFileInputDelimited_2.setLineEnd("" + rowSeparator_tFileInputDelimited_2[0]);

							csvReadertFileInputDelimited_2.setQuoteChar('"');

							csvReadertFileInputDelimited_2.setEscapeChar(csvReadertFileInputDelimited_2.getQuoteChar());

						}

						if (limittFileInputDelimited_2 != 0) {
							for (currentLinetFileInputDelimited_2 = 0; currentLinetFileInputDelimited_2 < 1; currentLinetFileInputDelimited_2++) {
								csvReadertFileInputDelimited_2.readNext();
							}
						}
						csvReadertFileInputDelimited_2.setSkipEmptyRecords(false);

					} catch (java.lang.Exception e) {
						globalMap.put("tFileInputDelimited_2_ERROR_MESSAGE", e.getMessage());

						System.err.println(e.getMessage());

					} // TD110 end

					while (limittFileInputDelimited_2 != 0 && csvReadertFileInputDelimited_2 != null
							&& csvReadertFileInputDelimited_2.readNext()) {
						rowstate_tFileInputDelimited_2.reset();

						rowtFileInputDelimited_2 = csvReadertFileInputDelimited_2.getValues();

						currentLinetFileInputDelimited_2++;

						if (lastLinetFileInputDelimited_2 > -1
								&& currentLinetFileInputDelimited_2 > lastLinetFileInputDelimited_2) {
							break;
						}
						outputLinetFileInputDelimited_2++;
						if (limittFileInputDelimited_2 > 0
								&& outputLinetFileInputDelimited_2 > limittFileInputDelimited_2) {
							break;
						}

						row5 = null;

						row5 = null;

						boolean whetherReject_tFileInputDelimited_2 = false;
						row5 = new row5Struct();
						try {

							char fieldSeparator_tFileInputDelimited_2_ListType[] = null;
							// support passing value (property: Field Separator) by 'context.fs' or
							// 'globalMap.get("fs")'.
							if (((String) ",").length() > 0) {
								fieldSeparator_tFileInputDelimited_2_ListType = ((String) ",").toCharArray();
							} else {
								throw new IllegalArgumentException("Field Separator must be assigned a char.");
							}
							if (rowtFileInputDelimited_2.length == 1 && ("\015").equals(rowtFileInputDelimited_2[0])) {// empty
																														// line
																														// when
																														// row
																														// separator
																														// is
																														// '\n'

								row5.code = null;

								row5.country = null;

								row5.country_long = null;

								row5.tag = null;

								row5.note = null;

							} else {

								int columnIndexWithD_tFileInputDelimited_2 = 0; // Column Index

								columnIndexWithD_tFileInputDelimited_2 = 0;

								if (columnIndexWithD_tFileInputDelimited_2 < rowtFileInputDelimited_2.length) {

									row5.code = rowtFileInputDelimited_2[columnIndexWithD_tFileInputDelimited_2];

								} else {

									row5.code = null;

								}

								columnIndexWithD_tFileInputDelimited_2 = 1;

								if (columnIndexWithD_tFileInputDelimited_2 < rowtFileInputDelimited_2.length) {

									row5.country = rowtFileInputDelimited_2[columnIndexWithD_tFileInputDelimited_2];

								} else {

									row5.country = null;

								}

								columnIndexWithD_tFileInputDelimited_2 = 2;

								if (columnIndexWithD_tFileInputDelimited_2 < rowtFileInputDelimited_2.length) {

									row5.country_long = rowtFileInputDelimited_2[columnIndexWithD_tFileInputDelimited_2];

								} else {

									row5.country_long = null;

								}

								columnIndexWithD_tFileInputDelimited_2 = 3;

								if (columnIndexWithD_tFileInputDelimited_2 < rowtFileInputDelimited_2.length) {

									row5.tag = rowtFileInputDelimited_2[columnIndexWithD_tFileInputDelimited_2];

								} else {

									row5.tag = null;

								}

								columnIndexWithD_tFileInputDelimited_2 = 4;

								if (columnIndexWithD_tFileInputDelimited_2 < rowtFileInputDelimited_2.length) {

									row5.note = rowtFileInputDelimited_2[columnIndexWithD_tFileInputDelimited_2];

								} else {

									row5.note = null;

								}

							}

							if (rowstate_tFileInputDelimited_2.getException() != null) {
								throw rowstate_tFileInputDelimited_2.getException();
							}

						} catch (java.lang.Exception e) {
							globalMap.put("tFileInputDelimited_2_ERROR_MESSAGE", e.getMessage());
							whetherReject_tFileInputDelimited_2 = true;

							System.err.println(e.getMessage());
							row5 = null;

							globalMap.put("tFileInputDelimited_2_ERROR_MESSAGE", e.getMessage());

						}

						/**
						 * [tFileInputDelimited_2 begin ] stop
						 */

						/**
						 * [tFileInputDelimited_2 main ] start
						 */

						currentComponent = "tFileInputDelimited_2";

						tos_count_tFileInputDelimited_2++;

						/**
						 * [tFileInputDelimited_2 main ] stop
						 */

						/**
						 * [tFileInputDelimited_2 process_data_begin ] start
						 */

						currentComponent = "tFileInputDelimited_2";

						/**
						 * [tFileInputDelimited_2 process_data_begin ] stop
						 */
// Start of branch "row5"
						if (row5 != null) {

							/**
							 * [tAdvancedHash_row5 main ] start
							 */

							currentComponent = "tAdvancedHash_row5";

							if (execStat) {
								runStat.updateStatOnConnection(iterateId, 1, 1

										, "row5"

								);
							}

							row5Struct row5_HashRow = new row5Struct();

							row5_HashRow.code = row5.code;

							row5_HashRow.country = row5.country;

							row5_HashRow.country_long = row5.country_long;

							row5_HashRow.tag = row5.tag;

							row5_HashRow.note = row5.note;

							tHash_Lookup_row5.put(row5_HashRow);

							tos_count_tAdvancedHash_row5++;

							/**
							 * [tAdvancedHash_row5 main ] stop
							 */

							/**
							 * [tAdvancedHash_row5 process_data_begin ] start
							 */

							currentComponent = "tAdvancedHash_row5";

							/**
							 * [tAdvancedHash_row5 process_data_begin ] stop
							 */

							/**
							 * [tAdvancedHash_row5 process_data_end ] start
							 */

							currentComponent = "tAdvancedHash_row5";

							/**
							 * [tAdvancedHash_row5 process_data_end ] stop
							 */

						} // End of branch "row5"

						/**
						 * [tFileInputDelimited_2 process_data_end ] start
						 */

						currentComponent = "tFileInputDelimited_2";

						/**
						 * [tFileInputDelimited_2 process_data_end ] stop
						 */

						/**
						 * [tFileInputDelimited_2 end ] start
						 */

						currentComponent = "tFileInputDelimited_2";

						nb_line_tFileInputDelimited_2++;
					}

				} finally {
					if (!(filename_tFileInputDelimited_2 instanceof java.io.InputStream)) {
						if (csvReadertFileInputDelimited_2 != null) {
							csvReadertFileInputDelimited_2.close();
						}
					}
					if (csvReadertFileInputDelimited_2 != null) {
						globalMap.put("tFileInputDelimited_2_NB_LINE", nb_line_tFileInputDelimited_2);
					}

				}

				ok_Hash.put("tFileInputDelimited_2", true);
				end_Hash.put("tFileInputDelimited_2", System.currentTimeMillis());

				/**
				 * [tFileInputDelimited_2 end ] stop
				 */

				/**
				 * [tAdvancedHash_row5 end ] start
				 */

				currentComponent = "tAdvancedHash_row5";

				tHash_Lookup_row5.endPut();

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row5");
				}

				ok_Hash.put("tAdvancedHash_row5", true);
				end_Hash.put("tAdvancedHash_row5", System.currentTimeMillis());

				/**
				 * [tAdvancedHash_row5 end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [tFileInputDelimited_2 finally ] start
				 */

				currentComponent = "tFileInputDelimited_2";

				/**
				 * [tFileInputDelimited_2 finally ] stop
				 */

				/**
				 * [tAdvancedHash_row5 finally ] start
				 */

				currentComponent = "tAdvancedHash_row5";

				/**
				 * [tAdvancedHash_row5 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tFileInputDelimited_2_SUBPROCESS_STATE", 1);
	}

	public String resuming_logs_dir_path = null;
	public String resuming_checkpoint_path = null;
	public String parent_part_launcher = null;
	private String resumeEntryMethodName = null;
	private boolean globalResumeTicket = false;

	public boolean watch = false;
	// portStats is null, it means don't execute the statistics
	public Integer portStats = null;
	public int portTraces = 4334;
	public String clientHost;
	public String defaultClientHost = "localhost";
	public String contextStr = "Default";
	public boolean isDefaultContext = true;
	public String pid = "0";
	public String rootPid = null;
	public String fatherPid = null;
	public String fatherNode = null;
	public long startTime = 0;
	public boolean isChildJob = false;
	public String log4jLevel = "";

	private boolean enableLogStash;

	private boolean execStat = true;

	private ThreadLocal<java.util.Map<String, String>> threadLocal = new ThreadLocal<java.util.Map<String, String>>() {
		protected java.util.Map<String, String> initialValue() {
			java.util.Map<String, String> threadRunResultMap = new java.util.HashMap<String, String>();
			threadRunResultMap.put("errorCode", null);
			threadRunResultMap.put("status", "");
			return threadRunResultMap;
		};
	};

	protected PropertiesWithType context_param = new PropertiesWithType();
	public java.util.Map<String, Object> parentContextMap = new java.util.HashMap<String, Object>();

	public String status = "";

	public static void main(String[] args) {
		final completude_nocs completude_nocsClass = new completude_nocs();

		int exitCode = completude_nocsClass.runJobInTOS(args);

		System.exit(exitCode);
	}

	public String[][] runJob(String[] args) {

		int exitCode = runJobInTOS(args);
		String[][] bufferValue = new String[][] { { Integer.toString(exitCode) } };

		return bufferValue;
	}

	public boolean hastBufferOutputComponent() {
		boolean hastBufferOutput = false;

		return hastBufferOutput;
	}

	public int runJobInTOS(String[] args) {
		// reset status
		status = "";

		String lastStr = "";
		for (String arg : args) {
			if (arg.equalsIgnoreCase("--context_param")) {
				lastStr = arg;
			} else if (lastStr.equals("")) {
				evalParam(arg);
			} else {
				evalParam(lastStr + " " + arg);
				lastStr = "";
			}
		}
		enableLogStash = "true".equalsIgnoreCase(System.getProperty("audit.enabled"));

		if (clientHost == null) {
			clientHost = defaultClientHost;
		}

		if (pid == null || "0".equals(pid)) {
			pid = TalendString.getAsciiRandomString(6);
		}

		if (rootPid == null) {
			rootPid = pid;
		}
		if (fatherPid == null) {
			fatherPid = pid;
		} else {
			isChildJob = true;
		}

		if (portStats != null) {
			// portStats = -1; //for testing
			if (portStats < 0 || portStats > 65535) {
				// issue:10869, the portStats is invalid, so this client socket can't open
				System.err.println("The statistics socket port " + portStats + " is invalid.");
				execStat = false;
			}
		} else {
			execStat = false;
		}
		boolean inOSGi = routines.system.BundleUtils.inOSGi();

		if (inOSGi) {
			java.util.Dictionary<String, Object> jobProperties = routines.system.BundleUtils.getJobProperties(jobName);

			if (jobProperties != null && jobProperties.get("context") != null) {
				contextStr = (String) jobProperties.get("context");
			}
		}

		try {
			// call job/subjob with an existing context, like: --context=production. if
			// without this parameter, there will use the default context instead.
			java.io.InputStream inContext = completude_nocs.class.getClassLoader()
					.getResourceAsStream("projettalend1/completude_nocs_0_1/contexts/" + contextStr + ".properties");
			if (inContext == null) {
				inContext = completude_nocs.class.getClassLoader()
						.getResourceAsStream("config/contexts/" + contextStr + ".properties");
			}
			if (inContext != null) {
				try {
					// defaultProps is in order to keep the original context value
					if (context != null && context.isEmpty()) {
						defaultProps.load(inContext);
						context = new ContextProperties(defaultProps);
					}
				} finally {
					inContext.close();
				}
			} else if (!isDefaultContext) {
				// print info and job continue to run, for case: context_param is not empty.
				System.err.println("Could not find the context " + contextStr);
			}

			if (!context_param.isEmpty()) {
				context.putAll(context_param);
				// set types for params from parentJobs
				for (Object key : context_param.keySet()) {
					String context_key = key.toString();
					String context_type = context_param.getContextType(context_key);
					context.setContextType(context_key, context_type);

				}
			}
			class ContextProcessing {
				private void processContext_0() {
				}

				public void processAllContext() {
					processContext_0();
				}
			}

			new ContextProcessing().processAllContext();
		} catch (java.io.IOException ie) {
			System.err.println("Could not load context " + contextStr);
			ie.printStackTrace();
		}

		// get context value from parent directly
		if (parentContextMap != null && !parentContextMap.isEmpty()) {
		}

		// Resume: init the resumeUtil
		resumeEntryMethodName = ResumeUtil.getResumeEntryMethodName(resuming_checkpoint_path);
		resumeUtil = new ResumeUtil(resuming_logs_dir_path, isChildJob, rootPid);
		resumeUtil.initCommonInfo(pid, rootPid, fatherPid, projectName, jobName, contextStr, jobVersion);

		List<String> parametersToEncrypt = new java.util.ArrayList<String>();
		// Resume: jobStart
		resumeUtil.addLog("JOB_STARTED", "JOB:" + jobName, parent_part_launcher, Thread.currentThread().getId() + "",
				"", "", "", "", resumeUtil.convertToJsonText(context, parametersToEncrypt));

		if (execStat) {
			try {
				runStat.openSocket(!isChildJob);
				runStat.setAllPID(rootPid, fatherPid, pid, jobName);
				runStat.startThreadStat(clientHost, portStats);
				runStat.updateStatOnJob(RunStat.JOBSTART, fatherNode);
			} catch (java.io.IOException ioException) {
				ioException.printStackTrace();
			}
		}

		java.util.concurrent.ConcurrentHashMap<Object, Object> concurrentHashMap = new java.util.concurrent.ConcurrentHashMap<Object, Object>();
		globalMap.put("concurrentHashMap", concurrentHashMap);

		long startUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		long endUsedMemory = 0;
		long end = 0;

		startTime = System.currentTimeMillis();

		this.globalResumeTicket = true;// to run tPreJob

		this.globalResumeTicket = false;// to run others jobs

		try {
			errorCode = null;
			tFileInputDelimited_1Process(globalMap);
			if (!"failure".equals(status)) {
				status = "end";
			}
		} catch (TalendException e_tFileInputDelimited_1) {
			globalMap.put("tFileInputDelimited_1_SUBPROCESS_STATE", -1);

			e_tFileInputDelimited_1.printStackTrace();

		}
		try {
			errorCode = null;
			tFileInputDelimited_3Process(globalMap);
			if (!"failure".equals(status)) {
				status = "end";
			}
		} catch (TalendException e_tFileInputDelimited_3) {
			globalMap.put("tFileInputDelimited_3_SUBPROCESS_STATE", -1);

			e_tFileInputDelimited_3.printStackTrace();

		}

		this.globalResumeTicket = true;// to run tPostJob

		end = System.currentTimeMillis();

		if (watch) {
			System.out.println((end - startTime) + " milliseconds");
		}

		endUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		if (false) {
			System.out.println(
					(endUsedMemory - startUsedMemory) + " bytes memory increase when running : completude_nocs");
		}

		if (execStat) {
			runStat.updateStatOnJob(RunStat.JOBEND, fatherNode);
			runStat.stopThreadStat();
		}
		int returnCode = 0;

		if (errorCode == null) {
			returnCode = status != null && status.equals("failure") ? 1 : 0;
		} else {
			returnCode = errorCode.intValue();
		}
		resumeUtil.addLog("JOB_ENDED", "JOB:" + jobName, parent_part_launcher, Thread.currentThread().getId() + "", "",
				"" + returnCode, "", "", "");

		return returnCode;

	}

	// only for OSGi env
	public void destroy() {

	}

	private java.util.Map<String, Object> getSharedConnections4REST() {
		java.util.Map<String, Object> connections = new java.util.HashMap<String, Object>();

		return connections;
	}

	private void evalParam(String arg) {
		if (arg.startsWith("--resuming_logs_dir_path")) {
			resuming_logs_dir_path = arg.substring(25);
		} else if (arg.startsWith("--resuming_checkpoint_path")) {
			resuming_checkpoint_path = arg.substring(27);
		} else if (arg.startsWith("--parent_part_launcher")) {
			parent_part_launcher = arg.substring(23);
		} else if (arg.startsWith("--watch")) {
			watch = true;
		} else if (arg.startsWith("--stat_port=")) {
			String portStatsStr = arg.substring(12);
			if (portStatsStr != null && !portStatsStr.equals("null")) {
				portStats = Integer.parseInt(portStatsStr);
			}
		} else if (arg.startsWith("--trace_port=")) {
			portTraces = Integer.parseInt(arg.substring(13));
		} else if (arg.startsWith("--client_host=")) {
			clientHost = arg.substring(14);
		} else if (arg.startsWith("--context=")) {
			contextStr = arg.substring(10);
			isDefaultContext = false;
		} else if (arg.startsWith("--father_pid=")) {
			fatherPid = arg.substring(13);
		} else if (arg.startsWith("--root_pid=")) {
			rootPid = arg.substring(11);
		} else if (arg.startsWith("--father_node=")) {
			fatherNode = arg.substring(14);
		} else if (arg.startsWith("--pid=")) {
			pid = arg.substring(6);
		} else if (arg.startsWith("--context_type")) {
			String keyValue = arg.substring(15);
			int index = -1;
			if (keyValue != null && (index = keyValue.indexOf('=')) > -1) {
				if (fatherPid == null) {
					context_param.setContextType(keyValue.substring(0, index),
							replaceEscapeChars(keyValue.substring(index + 1)));
				} else { // the subjob won't escape the especial chars
					context_param.setContextType(keyValue.substring(0, index), keyValue.substring(index + 1));
				}

			}

		} else if (arg.startsWith("--context_param")) {
			String keyValue = arg.substring(16);
			int index = -1;
			if (keyValue != null && (index = keyValue.indexOf('=')) > -1) {
				if (fatherPid == null) {
					context_param.put(keyValue.substring(0, index), replaceEscapeChars(keyValue.substring(index + 1)));
				} else { // the subjob won't escape the especial chars
					context_param.put(keyValue.substring(0, index), keyValue.substring(index + 1));
				}
			}
		} else if (arg.startsWith("--log4jLevel=")) {
			log4jLevel = arg.substring(13);
		} else if (arg.startsWith("--audit.enabled") && arg.contains("=")) {// for trunjob call
			final int equal = arg.indexOf('=');
			final String key = arg.substring("--".length(), equal);
			System.setProperty(key, arg.substring(equal + 1));
		}
	}

	private static final String NULL_VALUE_EXPRESSION_IN_COMMAND_STRING_FOR_CHILD_JOB_ONLY = "<TALEND_NULL>";

	private final String[][] escapeChars = { { "\\\\", "\\" }, { "\\n", "\n" }, { "\\'", "\'" }, { "\\r", "\r" },
			{ "\\f", "\f" }, { "\\b", "\b" }, { "\\t", "\t" } };

	private String replaceEscapeChars(String keyValue) {

		if (keyValue == null || ("").equals(keyValue.trim())) {
			return keyValue;
		}

		StringBuilder result = new StringBuilder();
		int currIndex = 0;
		while (currIndex < keyValue.length()) {
			int index = -1;
			// judege if the left string includes escape chars
			for (String[] strArray : escapeChars) {
				index = keyValue.indexOf(strArray[0], currIndex);
				if (index >= 0) {

					result.append(keyValue.substring(currIndex, index + strArray[0].length()).replace(strArray[0],
							strArray[1]));
					currIndex = index + strArray[0].length();
					break;
				}
			}
			// if the left string doesn't include escape chars, append the left into the
			// result
			if (index < 0) {
				result.append(keyValue.substring(currIndex));
				currIndex = currIndex + keyValue.length();
			}
		}

		return result.toString();
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public String getStatus() {
		return status;
	}

	ResumeUtil resumeUtil = null;
}
/************************************************************************************************
 * 200829 characters generated by Talend Open Studio for Data Integration on the
 * 3 janvier 2025 à 10:44:35 CET
 ************************************************************************************************/