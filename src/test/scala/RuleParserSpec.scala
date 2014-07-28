package ruleparsing

import org.scalatest.FlatSpec

import scala.io.Source

trait Fixtures {
  val rulesStream = getClass.getClassLoader.getResourceAsStream("rules.txt")
  val rulesSource = Source.fromInputStream(rulesStream)
  val rules = RuleParser.parse(rulesSource).toList
  val kb = new KB(rules)
}

class RuleParserSpec extends FlatSpec {

  "parse" should "parse all rules" in new Fixtures {
    assert(rules.size === 4)
    assert(rules(0).english === "Animals and plants are living things")
    assert(rules(0).pretty === "EXAMPLE(Animal, Thing), Animal = 'Animals', Thing = 'living things'.")
    assert(rules(0).ruleLhs === "isa(A1S4-animal, \"Animals\")")
    assert(rules(0).ruleRhs === "example(A1S4-animal, A6S4-thing), isa(A6S4-thing, \"living things\")")
  }
}

class KBSpec extends FlatSpec {
  "getRule" should "work" in new Fixtures {
    assert(kb.getRule("barrons.rule2") === rules.toList(1))

  }
  "getRuleEnglish" should "work" in new Fixtures {
    assert(kb.getRuleEnglish("barrons.rule2") === "Animals and plants are living things")
  }

  "getRulePrettyPrint" should "work" in new Fixtures {
    assert(kb.getRulePrettyPrint("barrons.rule2") === "EXAMPLE(Animal, Thing), Animal = 'Animals', Thing = 'living things'.")
  }
}
