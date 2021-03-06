/*
 * Copyright (c) 2018 datagear.tech. All Rights Reserved.
 */

/**
 * 
 */
package org.datagear.analysis.support;

import java.util.List;
import java.util.Map;

import org.datagear.analysis.DataSetException;
import org.datagear.analysis.DataSetProperty;
import org.datagear.analysis.DataSetResult;

/**
 * JSON字符串值数据集。
 * <p>
 * 此类的{@linkplain #getValue()}支持<code>Freemarker</code>模板语言。
 * </p>
 * 
 * @author datagear@163.com
 *
 */
public class JsonValueDataSet extends AbstractJsonDataSet
{
	private String value;

	public JsonValueDataSet()
	{
		super();
	}

	public JsonValueDataSet(String id, String name, String value)
	{
		super(id, name);
		this.value = value;
	}

	public JsonValueDataSet(String id, String name, List<DataSetProperty> properties, String value)
	{
		super(id, name, properties);
		this.value = value;
	}

	public String getValue()
	{
		return value;
	}

	public void setValue(String value)
	{
		this.value = value;
	}

	@Override
	public DataSetResult getResult(Map<String, ?> paramValues) throws DataSetException
	{
		String json = resolveTemplate(this.value, paramValues);

		Object data = getJsonDataSetSupport().resolveResultData(json);

		return new DataSetResult(data);
	}

	@Override
	public TemplateResolvedDataSetResult resolve(Map<String, ?> paramValues) throws DataSetException
	{
		String json = resolveTemplate(this.value, paramValues);

		Object data = getJsonDataSetSupport().resolveResultData(json);
		DataSetResult result = new DataSetResult(data);

		List<DataSetProperty> properties = getJsonDataSetSupport().resolveDataSetProperties(result.getData());

		return new TemplateResolvedDataSetResult(result, properties, json);
	}
}
