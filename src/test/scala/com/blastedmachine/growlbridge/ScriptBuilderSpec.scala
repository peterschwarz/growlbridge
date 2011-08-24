package com.blastedmachine.growlbridge

import org.junit.runner.RunWith
import org.scalatest.junit.ShouldMatchersForJUnit
import org.scalatest.FlatSpec
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class ScriptBuilderSpec extends FlatSpec with ShouldMatchersForJUnit {
  
  "A ScriptBuilder" should "produce an empty script with no additions" in {
    val script = ScriptBuilder().get();
    
    script should be ("")
  }

  it should "add simple strings, and output them as the were added" in {
    ScriptBuilder().add("a string").get() should be ("a string")
  }
  
  it should "output a list in the correct format" in {
    ScriptBuilder().array(List("one", "two", "three")).get should be ("""{"one", "two", "three"}""")
  }
  
  it should "quote a string correctly" in {
    ScriptBuilder().quote("a String").get should be ("\"a String\"")
  }
  
  it should "quote a filename" in {
    ScriptBuilder().filename("file:///foo/bar").get should be ("\"file:///foo/bar\"")
  }
  
  it should "quote and correct a filename" in {
    ScriptBuilder().filename("file:/foo/bar").get should be ("\"file:///foo/bar\"")
  }
  
}