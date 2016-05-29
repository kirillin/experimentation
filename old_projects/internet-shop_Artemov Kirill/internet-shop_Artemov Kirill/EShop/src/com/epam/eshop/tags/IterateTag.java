package com.epam.eshop.tags;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.log4j.Logger;

import com.epam.eshop.manager.MessageManager;
import com.epam.eshop.tags.exception.TagException;

/**
 * Tag for printing collections in JSP
 * 
 * @author Kirill Artemov
 * 
 */
public class IterateTag extends BodyTagSupport {

	private static Logger logger = Logger.getLogger(IterateTag.class);
	private static final long serialVersionUID = 1L;

	private Collection collection;
	private String id;
	private Iterator iterator;

	public IterateTag() {
		this.collection = null;
		this.id = null;
		this.iterator = null;
	}

	/**
	 * @return the collection
	 */
	public Collection getCollection() {
		return collection;
	}

	/**
	 * @param collection
	 *            the collection to set
	 */
	public void setCollection(Collection collection) {
		if (collection instanceof Collection) {
			this.collection = collection;
		} else
			collection = null;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	public int doStartTag() throws JspException {
		Collection clt = null;
		try {
			if (this.collection != null) {
				clt = this.collection;
			} else
				throw new TagException();
		} catch (TagException e) {
			logger.trace("TagException" + e);
		}
		iterator = clt.iterator();
		if (iterator.hasNext()) {
			Object element = iterator.next();
			pageContext.setAttribute(id, element);
			return (EVAL_BODY_AGAIN);
		} else
			return (SKIP_BODY);
	}

	public int doAfterBody() throws JspException {
		BodyContent bodyContent = getBodyContent();
		if (bodyContent != null) {
			try {
				JspWriter out = getPreviousOut();
				out.print(bodyContent.getString());
				bodyContent.clearBody();
			} catch (IOException e) {
				logger.error(MessageManager.getInstance().getProperty(
						MessageManager.IO_EXCEPTION_ERROR_MESSAGE));
			}
		}
		if (iterator.hasNext()) {
			Object element = iterator.next();
			pageContext.setAttribute(id, element);
			return (EVAL_BODY_AGAIN);
		} else {
			return (SKIP_BODY);
		}
	}

	@Override
	public int doEndTag() throws JspException {
		return super.doEndTag();
	}
}
