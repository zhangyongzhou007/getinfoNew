package com.QuestionBank.utils;

import org.dom4j.Attribute;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.QName;

import java.util.ArrayList;
import java.util.List;

public class IQSchema
{

	private Element operate;
	
	public IQSchema() {
		operate = DocumentHelper.createElement(QName.get("operate", "jabber:iq:group"));
	}
	
	public IQSchema(Element operate){
		this.operate = operate;
	}

	public Element getOperate() {
		return operate;
	}

	public void setOperate(Element operate) {
		this.operate = operate;
	}
	
	public void setAttribute(String name, String value){
		Attribute operateAttribute = operate.attribute(name);
		if (operateAttribute == null) {
			operate.addAttribute(name, value);
		} else {
			operateAttribute.setValue(value);
		}
	}
	
	public String getAttribute(String name){
		Attribute operateAttribute = operate.attribute(name);
		if(operateAttribute != null) {
			return operateAttribute.getValue();
		}
		
		return null;
	}
	
	public Item addItem(){
		Element item = operate.addElement("item");
		return new Item(item);
	}
	
	@SuppressWarnings("unchecked")
	public List<Item> getItemList(){
		List<Item> items = new ArrayList<Item>();
		
		List<Element> elements = operate.elements("item");
		
		for(Element tmp:elements){
			items.add(new Item(tmp));
		}
		
		return items;
	} 
	
	@SuppressWarnings("unchecked")
	public Item getItem(){
		List<Item> items = new ArrayList<Item>();
		
		List<Element> elements = operate.elements("item");
		
		for(Element tmp:elements){
			items.add(new Item(tmp));
		}
		
		return items == null ? null : items.get(0);
	} 
	
	public class Item {
		private Element itemElement;
		
		public Item(Element itemElement){
			this.itemElement = itemElement;
		}
		
		/**
		 * 设置属性
		 * @Title: setAttribute 
		 * @Description: 设置属性
		 * @param name
		 * @param value void
		 * @throws
		 */
		public void setAttribute(String name, String value) {
			Attribute itemAttribute = itemElement.attribute(name);
			
			if (itemAttribute == null) {
				itemElement.addAttribute(name, value);
			} else {
				itemAttribute.setValue(value);
			}

		}	
		
		public String getAttribute(String name){
			Attribute itemAttribute = itemElement.attribute(name);
			
			if (itemAttribute == null) {
				return null;
			} else {
				return itemAttribute.getValue();
			}
		}

		/**
		 * 添加Element
		 * @Title: addElement 
		 * @Description: 添加Element
		 * @param name
		 * @return Element
		 * @throws
		 */
		public Element addElement(String name) {
			return itemElement.addElement(name);			

		}
		
		public Element getElement(String name) {
			return itemElement.element(name);	
		}
		
		@SuppressWarnings("unchecked")
		public List<Element> getElementList(String name) {
			return itemElement.elements(name);	
		}
		
		public Table addTable(){
			Element table = itemElement.addElement("table");
			return new Table(table);
		}
		
		
		public class Table {
			private Element tableElement;
			
			public Table(Element tableElement) {
				this.tableElement = tableElement;
			}
			
			/**
			 * 设置属性
			 * @Title: setAttribute 
			 * @Description: 设置属性
			 * @param name
			 * @param value void
			 * @throws
			 */
			public void setAttribute(String name, String value) {
				Attribute tableAttribute = tableElement.attribute(name);
				
				if (tableAttribute == null) {
					tableElement.addAttribute(name, value);
				} else {
					tableAttribute.setValue(value);
				}

			}	
			
			public String getAttribute(String name){
				Attribute tableAttribute = tableElement.attribute(name);
				
				if (tableAttribute == null) {
					return null;
				} else {
					return tableAttribute.getValue();
				}
			}

			/**
			 * 添加Element
			 * @Title: addElement 
			 * @Description: 添加Element
			 * @param name
			 * @return Element
			 * @throws
			 */
			public Element addElement(String name) {
				return tableElement.addElement(name);			

			}
			
			public Element getElement(String name) {
				return tableElement.element(name);	
			}
			
			@SuppressWarnings("unchecked")
			public List<Element> getElementList(String name) {
				return tableElement.elements(name);	
			}
		}
		
	}
	
	
	
	
	

}
