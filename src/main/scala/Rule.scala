package ruleparsing

sealed trait RuleElement {
  def ruleId: String
}

case class Rule(ruleId: String, english: String, pretty: String, ruleLhs: String, ruleRhs: String)
