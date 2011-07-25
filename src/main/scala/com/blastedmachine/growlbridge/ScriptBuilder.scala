package com.blastedmachine.growlbridge

private[growlbridge] object ScriptBuilder
{
  def apply(): ScriptBuilder = new ScriptBuilder
}

private[growlbridge] class ScriptBuilder {
  val builder = new StringBuilder();

  def add(text: String): ScriptBuilder = {
    builder.append(text);
    return this;
  }

  def quote(text: String): ScriptBuilder = {
    builder.append("\"");
    builder.append(text);
    builder.append("\"");
    return this;
  }

  def nextRow(text: String): ScriptBuilder = {
    builder.append("\n");
    builder.append(text);
    return this;
  }

  def get(): String = {
    builder.toString();
  }

  def array(array: Seq[String]): ScriptBuilder = {
    builder.append("{");

    builder.append(array.map("\"%s\"".format(_)).mkString(", "))

    builder.append("}");
    return this;
  }
}