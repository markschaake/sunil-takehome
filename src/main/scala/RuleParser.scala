package ruleparsing

import scala.io.BufferedSource

object RuleParser {
  val ruleIdPatt = """[A-Za-z][a-zA-Z0-9._-]*"""
  val EnglishPatt = s"""^english\\((${ruleIdPatt}), "([^"]+)"\\)\\.$$""".r
  val PrettyPatt = s"""^pretty\\((${ruleIdPatt}), "([^"]+)"\\)\\.$$""".r
  val RulePatt = s"""^(${ruleIdPatt})::([^.]+)\\.$$""".r
  def parse(bufferedSource: BufferedSource): Iterable[Rule] = {
    val itr = bufferedSource.getLines.grouped(3)
    val rules = itr map {
      case List(english, pretty, rule) =>
        val EnglishPatt(englishRuleId, englishText) = english
        val PrettyPatt(prettyRuleId, prettyText) = pretty
        val RulePatt(ruleId, rest) = rule
        val (lhs, rhs) = rest.split("->") match {
          case Array(l, r) => (l.trim, r.trim)
          case _ => throw new Exception("Invalid rule format")
        }

        Rule(ruleId, englishText, prettyText, lhs, rhs)
    }
    rules.toList
  }
}
