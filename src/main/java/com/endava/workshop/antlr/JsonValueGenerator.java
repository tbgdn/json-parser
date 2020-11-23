package com.endava.workshop.antlr;

import com.endava.workshop.JsonBaseListener;
import com.endava.workshop.JsonParser.FalseValueContext;
import com.endava.workshop.JsonParser.FieldDefinitionContext;
import com.endava.workshop.JsonParser.IntegerValueContext;
import com.endava.workshop.JsonParser.ObjContext;
import com.endava.workshop.JsonParser.StringValueContext;
import com.endava.workshop.JsonParser.TrueValueContext;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.Deque;
import java.util.Map;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.antlr.v4.runtime.tree.TerminalNode;

@Slf4j
@Getter
public class JsonValueGenerator extends JsonBaseListener {

  private Map<String, Object> value = Maps.newHashMap();
  private Deque<String> fieldsStack = Lists.newLinkedList();
  private Deque<Map<String, Object>> objectsStack = Lists.newLinkedList();

  @Override
  public void enterObj(ObjContext ctx) {
    objectsStack.push(Maps.newHashMap());
  }

  @Override
  public void exitObj(ObjContext ctx) {
    if (fieldsStack.isEmpty()){
      this.value = this.objectsStack.pop();
    }else{
      Map<String, Object> obj = this.objectsStack.pop();
      parentObject().put(fieldsStack.pop(), obj);
    }
  }

  @Override
  public void enterFieldDefinition(FieldDefinitionContext ctx) {
    fieldsStack.push(extractString(ctx.STRING()));
  }

  @Override
  public void enterStringValue(StringValueContext ctx) {
    parentObject().put(fieldsStack.pop(), extractString(ctx.STRING()));
  }

  @Override
  public void enterIntegerValue(IntegerValueContext ctx) {
    parentObject().put(fieldsStack.pop(), extractNumber(ctx.NUMBER()));
  }

  @Override
  public void enterTrueValue(TrueValueContext ctx) {
    parentObject().put(fieldsStack.pop(), extractBoolean(ctx.getText()));
  }

  @Override
  public void enterFalseValue(FalseValueContext ctx) {
    parentObject().put(fieldsStack.pop(), extractBoolean(ctx.getText()));
  }

  private Boolean extractBoolean(String text) {
    return Boolean.parseBoolean(text);
  }

  private String extractString(TerminalNode string) {
    String stringValue = string.getText().substring(1);
    stringValue = stringValue.substring(0, stringValue.length() - 1);
    return stringValue;
  }

  private Integer extractNumber(TerminalNode number) {
    return Integer.parseInt(number.getText());
  }

  private Map<String, Object> parentObject() {
    return this.objectsStack.peek();
  }
}
