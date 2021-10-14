package com.example.lxs.XML_analysis;

import android.util.Log;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAX  extends DefaultHandler {
    public String node_name;
    public StringBuilder to;
    public StringBuilder from;
    public StringBuilder heading;
    public StringBuilder body;

    @Override
    public void startDocument() throws SAXException {
        to=new StringBuilder();
        from=new StringBuilder();
        heading=new StringBuilder();
        body=new StringBuilder();
    }
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        node_name=localName;
    }
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if ("to".equals(node_name)){
            to.append(ch,start,length);
        }else if ("from".equals(node_name)){
            from.append(ch,start,length);
        }else if("heading".equals(node_name)){
            heading.append(ch,start,length);
        }else if("body".equals(node_name)){
            body.append(ch,start,length);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if ("note".equals(localName)){
            Log.d("to",to.toString().trim());
            Log.d("from",from.toString().trim());
            Log.d("heading",heading.toString().trim());
            Log.d("body",body.toString().trim());
//            to.setLength(0);
//            from.setLength(0);
//            heading.setLength(0);
//            body.setLength(0);
        }
    }
    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }
}
