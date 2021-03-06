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
    builder.append(quotedString(text));
    return this;
  }
  
  def filename(filename: String): ScriptBuilder = {
    if (filename.indexOf("file:/") == 0 && filename.charAt(6) != '/')
        builder.append(quotedString("file:///" + filename.substring(6)))
    else
      builder.append(quotedString(filename))
    return this
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

    builder.append(array.map(quotedString(_)).mkString(", "))

    builder.append("}");
    return this;
  }
  
  private def quotedString(text: String) = "\"%s\"".format(text)
}