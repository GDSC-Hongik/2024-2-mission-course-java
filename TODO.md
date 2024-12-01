# 턴제 게임

### 객체 설계
1. 캐릭터 (Character)
2. 행동 (Action)

~~~mermaid
classDiagram
    class Character {
        -String name
        -int healthPoint
        -int manaPoint
        -int defense
        +takeDamage(int) void
        +setDefense(int) void
        +useMana(int) void
        +isAlive() boolean
    }

    class Action {
        <<interface>>
        +execute(Character, Character) void
    }

    class AttackAction {
        -int MIN_DAMAGE
        -int MAX_DAMAGE
        -Random random
        +execute(Character, Character) void
    }

    class DefenseAction {
        -int MIN_DEFENSE
        -int MAX_DEFENSE
        -Random random
        +execute(Character, Character) void
    }

    class SkillAction {
        <<abstract>>
        #int manaCost
        #int coolTime
        #int remainingCooltime
        #Random random
        +SkillAction(manaCost, coolTime)
        +reduceCoolTime() void
        +execute(Character, Character) void
    }

    class CutTwice {
        -int MIN_DAMAGE
        -int MAX_DAMAGE
        -int MANA_COST
        -int COOL_TIME
        +CutTwice()
        +execute(Character, Character) void
    }

    class CutThreeTimes {
        -int MIN_DAMAGE
        -int MAX_DAMAGE
        -int MANA_COST
        -int COOL_TIME
        +CutThreeTimes()
        +execute(Character, Character) void
    }

    class PowerStrike {
        -int MIN_DAMAGE
        -int MAX_DAMAGE
        -int MANA_COST
        -int COOL_TIME
        +PowerStrike()
        +execute(Character, Character) void
    }

    Action <|.. AttackAction : implements
    Action <|.. DefenseAction : implements
    Action <|.. SkillAction : implements
    SkillAction <|-- CutTwice : extends
    SkillAction <|-- CutThreeTimes : extends
    SkillAction <|-- PowerStrike : extends
~~~
### 기능 목록
| 객체      | 기능                                                    |
| ------- | ----------------------------------------------------- |
| **캐릭터** | **행동에 따른 체력, 마나, 데미지 감소를 받는다**                          |
|         | - (상대방이) 공격 수행시, 받은 데미지 만큼 체력이 감소한다                   |
|         | - (현재캐릭터가) 방어 수행시, 랜덤한 방어력을 설정해주고 상대방에게 받는 데미지를 감소시킨다 |
|         | - 스킬 사용시, 각 스킬에 따른 마나를 소모한다                           |
| **행동**  |**각 행동(공격, 방어, 스킬)을 수행한다**                          |
|         | - 공격 : 상대방에게 랜덤한 데미지를 입힌다                             |
|         | - 방어 : 현재 캐릭터에게 랜덤한 방어력을 설정한다                         |
|         | - 스킬 : 상대방에게 각 스킬(2번 베기, 3번 베기, 세게때리기)에 따른 데미지를 입힌다   |

