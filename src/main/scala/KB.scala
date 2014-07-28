package ruleparsing

class KB(rules: Iterable[Rule]) {

  private val rulesMap = rules.map(r => r.ruleId -> r).toMap

  def getRule(ruleId: String): Rule = rulesMap(ruleId)

  def getRuleEnglish(ruleId: String): String = getRule(ruleId).english

  def getRulePrettyPrint(ruleId: String): String = getRule(ruleId).pretty
}
