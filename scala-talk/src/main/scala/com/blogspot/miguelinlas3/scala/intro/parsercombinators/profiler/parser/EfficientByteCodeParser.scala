package com.blogspot.miguelinlas3.scala.intro.parsercombinators.profiler.parser

import scala.util.parsing.combinator._
import util.parsing.input.CharSequenceReader
import com.blogspot.miguelinlas3.scala.intro.parsercombinators.instruction._

class EfficientByteCodeParser extends JavaTokenParsers {

  lazy val integer: Parser[Int] = """-?[\d]+""".r ^^ {
    _.toInt
  }

  lazy val startDefinition = """([^"\p{Cntrl}\\]|\\[\\/bfnrt]|\\u[a-fA-F0-9]{4})*""".r ~ "Code:"

  // this is the metainfo (num of local variables, stack size, number of args)
  lazy val metaInfo: Parser[List[BytecodeInstruction]] = (("Stack=" ~> integer) ~> (", Locals=" ~> integer)) ~ (", Args_size=" ~> integer) ^^ {
    case locals ~ args => new HeaderInstruction("HeaderInstruction", locals, args) :: List()
  }

  // all the bytecode lines start with this prefix :
  lazy val beginInstructionFragment: Parser[Int] = integer <~ ":" ^^ { _.toInt}

  //  all bytecode instructions supported for our profiler
  lazy val iConstBytecode: Parser[List[BytecodeInstruction]] = beginInstructionFragment ~> "iconst_" ~ integer ^^ {
    case name ~ value => new IConstInstruction(name,value) :: List()
  }

  lazy val iStoreBytecode: Parser[List[BytecodeInstruction]] = beginInstructionFragment ~> "istore_" ~ integer ^^ {
    case name ~ value => new IStoreInstruction(name,value)  :: List()
  }

  lazy val iStoreIndexBytecode: Parser[List[BytecodeInstruction]] = beginInstructionFragment ~> "istore" ~ integer ^^ {
    case name ~ value => new IStoreIndexInstruction(name) :: new ArgumentInstruction(value)::List()
  }

  lazy val iLoadBytecode: Parser[List[BytecodeInstruction]] = beginInstructionFragment ~> "iload_" ~ integer ^^ {
    case name ~ value => new ILoadInstruction(name,value) :: List()
  }

  lazy val iLoadIndexBytecode: Parser[List[BytecodeInstruction]] = beginInstructionFragment ~> "iload" ~ integer ^^ {
    case name ~ value => new ILoadIndexInstruction(name) :: new ArgumentInstruction(value)::List()
  }

  lazy val iIfIcmpgtByteCode: Parser[List[BytecodeInstruction]] = beginInstructionFragment ~ "if_icmpgt" ~ integer ^^ {
    case start ~ name ~ value => new IfIcmpgtInstruction(name) :: new ArgumentInstruction(start + 3) :: new ArgumentInstruction(value):: List()
  }

  lazy val iINeByteCode: Parser[List[BytecodeInstruction]] = beginInstructionFragment ~ "ifne" ~ integer ^^ {
    case start ~ name ~ value => new IfNeInstruction(name) :: new ArgumentInstruction(start + 3) :: new ArgumentInstruction(value):: List()
  }

  lazy val iILeByteCode: Parser[List[BytecodeInstruction]] = beginInstructionFragment ~ "ifle" ~ integer ^^{
    case start ~ name ~ value => new IfLeInstruction(name):: new ArgumentInstruction(start + 3) :: new ArgumentInstruction(value) :: List()
  }

  lazy val iReturnByteCode: Parser[List[BytecodeInstruction]] = beginInstructionFragment ~> "ireturn" ^^ {
    new IReturnInstruction(_)  :: List()
  }

  lazy val returnByteCode: Parser[List[BytecodeInstruction]] = beginInstructionFragment ~> "return" ^^ {
    new ReturnInstruction(_)     :: List()
  }

  lazy val iGotoByteCode: Parser[List[BytecodeInstruction]] = beginInstructionFragment ~> "goto" ~ integer ^^ {
   case name ~ value =>  new GotoInstruction(name) :: new ArgumentInstruction(value) :: new ArgumentInstruction(value) :: List()
  }

  lazy val iMulByteCode: Parser[List[BytecodeInstruction]] = beginInstructionFragment ~> "imul" ^^ {
    new IMulInstruction(_)     :: List()
  }

  lazy val iDivByteCode: Parser[List[BytecodeInstruction]] = beginInstructionFragment ~> "idiv" ^^ {
    new IDivInstruction(_)     :: List()
  }

  lazy val iAddByteCode: Parser[List[BytecodeInstruction]] = beginInstructionFragment ~> "iadd" ^^ {
    new ISumInstruction(_)  :: List()
  }

  lazy val iSubByteCode: Parser[List[BytecodeInstruction]] = beginInstructionFragment ~> "isub" ^^ {
    new ISubInstruction(_)  :: List()
  }

  lazy val dupByteCode: Parser[List[BytecodeInstruction]] = beginInstructionFragment ~> "dup" ^^ {
    new DupInstruction(_) :: List()
  }

  lazy val iIncByteCode: Parser[List[BytecodeInstruction]] = beginInstructionFragment ~ "iinc" ~ integer ~ (", " ~> integer) ^^ {
    case start ~ name ~ variable ~ increment => new IIncInstruction(name):: new ArgumentInstruction(variable) :: new ArgumentInstruction(increment) ::  List()
  }

  // program bytecodes
  lazy val program: Parser[List[BytecodeInstruction]] = startDefinition ~> metaInfo ~ rep(iConstBytecode | iStoreBytecode | iStoreIndexBytecode | iLoadBytecode | iLoadIndexBytecode | iIfIcmpgtByteCode | iINeByteCode | iILeByteCode |iReturnByteCode | returnByteCode | iGotoByteCode | iMulByteCode | iDivByteCode | iAddByteCode | iSubByteCode | iIncByteCode | dupByteCode) ^^ {
    case header ~ instructions => header ::: instructions.flatten[BytecodeInstruction]
  }

  /**
   * Parse the file passed in the function argument an returns de the
   */
  def parse(file: String): List[BytecodeInstruction] = {
    val contents = scala.io.Source.fromFile(file).mkString

    val instructions = program(new CharSequenceReader(contents)) match {
      case s: Success[List[BytecodeInstruction]] => s.get
      case e: Error => List[BytecodeInstruction]()
    }

    instructions
  }
}