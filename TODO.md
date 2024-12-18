# 턴제 게임 TODO
# 1주차
## _구현 기능_
### 상황 설정
두 명의 플레이어가 하나의 컴퓨터 앞에 나란히 앉는다.  
application 실행 시 터미널 창에 플레이어 이름, 진행하고자 하는 턴 수를 입력한다.

게임을 시작한다.

### 게임 중..

플레이어 1의 행동 선택 후, 플레이어 2의 행동을 선택한다. 두 플레이어의 행동이 모두 끝난 후 결과를 출력하며,
#### 이것을 하나의 턴이 '종료' 되었다고 정의한다.

주어진 턴까지 도달하거나, 주어진 턴까지 진행되는 도중 hp가 0인 사람이 발생한다면 게임은 종료된다.

할 수 있는 행위는 공격, 방어, 스킬이 있다. 공격과 방어는 기본 행위로 취급한다.   
스킬은 특수한 행위로 취급한다. 마나를 소모하며, 한 번의 스킬 사용 후 일정 쿨타임 턴이 소요된다.

### 게임 종료
게임 종료 메세지와 함께 터미널 창을 종료한다.

## Object Diagram(mermaid)
```mermaid
classDiagram

    class Application {
        -Scanner scanner
        -int NUM_OF_ACTIONS
        -int NUM_OF_SKILLS
        +main(String[] args)
        -inputTwoPlayerName()
        +inputNumOfTurns()
    }

    class Action {
        <<interface>>
        +String getName()
        +ActionResult execute(Player user, Player target)
        +int getMinDamage()
        +int getMaxDamage()
    }

    class BasicAction {
        <<interface>>
        +int getRandomDamage()
    }

    class Attack {
        -String name

        +String getName()
        +int getRandomDamage()
        +ActionResult execute(Player user, Player target)
        +int getMaxDamage()
        +int getMinDamage()
    }

    class Defense {
        -String name

        +String getName()
        +int getRandomDamage()
        +ActionResult execute(Player user, Player target)
        +int getMaxDamage()
        +int getMinDamage()
    }

    class Skill {
        -String name
        -int mpCost
        -int coolDownTurns
        -int damage

        +int getMaxDamage()
        +int getMinDamage()
        -int calculateSkillDamage(int mpCost)
        +ActionResult execute(Player user, Player target)
        +String getName()
        +int getCoolDownTurns()
        +ActionResult execute()
    }

    class Player {
        -String name
        -int hp
        -int mp
        -List<Action> actions
        -Map<String, Integer> skillCooldowns

        +applyDamage(int damage)
        +applySkillCooldown(Skill skill)
        +boolean canUseSkill(String skillName)
        +updateCooldowns()
        +getActions()
        +getSkillCooldowns()
        +getHp()
        +getMp()
        +getName()
    }

    class ActionResult {
        -String actionName
        -int damage
        -String message

        +getActionName()
        +getDamage()
        +getMessage()

    }

    class TurnManager {
        -Scanner scanner
        +void startGame(Player player1, Player player2, int maxTurns)
        -void playTurn(Player player1, Player player2)
        -Action chooseAction(Player player)
        -void processResults(Player player1, ActionResult result1, Player player2, ActionResult result2)
        -void printPlayerState(Player player1, Player player2)
        -void printActions(Player player)
    }

    Application --> TurnManager
    Application --> Player
    Application --> Action

    Action <|-- BasicAction
    Action <|-- Skill
    BasicAction <|-- Attack
    BasicAction <|-- Defense
    ActionResult --> Player
    ActionResult --> Action
    Player --> Action
    Player --> Skill
    TurnManager --> Player
    TurnManager --> Action
    TurnManager --> ActionResult
    
```

## 클래스 소개

### Application
게임을 진행하기에 앞서 플레이어명과 진행 턴 수를 입력받는다.

플레이어의 사전 입력을 바탕으로 플레이어 정보를 생성하며,  
main 함수 내에 하드코딩을 통해 생성한 행위 목록을 플레이어 별로 세팅해준다.  
현재 두 플레이어 모두 할 수 있는 행위가 같지만,  
코드 수정을 통해 서로 다른 행동 선택지를 부여할 수 있도록 구현하였다.

게임 진행은 `TurnManager`에게 책임을 맡긴다.

정리하자면,  
게임 사전 세팅 담당 클래스이다.
### TurnManager
게임 진행을 관리하는 매니저 클래스이다.

각 플레이어의 현재 상태, 플레이어의 선택지 등을 터미널에 출력한다.  
정해진 양식에 따라 플레이어가 행동을 선택하면 행동 특성(공격, 방어, 스킬)에 따른 결과를  
하나의 턴 종료 후 터미널에 출력한다.


### ActionResult
공격, 방어, 스킬 중에 방어 행위 케이스 분류가 필요했다.
- 두 플레이어가 방어하는 경우
- 둘 다 공격하는 경우
- 한 명만 방어하는 경우

위 경우를 고려하기 위해서는 선공 플레이어의 행위 선택을 후공 플레이어의 선택까지 따로 저장한 후,  
한 턴이 끝난 후에 두 명의 행위의 복합적인 결과를 반영해야만 했다.

이를 위해, 

하나의 플레이어의 행위 선택 결과(데미지 결정, 행위 유형)를 저장하기 위한 클래스이다.
### Player
플레이어 클래스이다.

### Action
행위 인터페이스이다.

기본 행위(공격, 방어)와 스킬을 자식 클래스로 가진다.

### BasicAction
행위 인터페이스를 상속하는 기본 행위 인터페이스이다.  
공격, 방어 클래스를 자식 클래스로 가진다.

### Attack
공격 클래스이다.
기본 행위 인터페이스를 상속한다.
### Defend
방어 클래스이다.
기본 행위 인터페이스를 상속한다.

### Skill
스킬 클래스이다.
'소모 마나'와 '쿨타임 턴수'라는 특수한 필드를 가진다.   
행위 인터페이스를 상속한다.

## [소스 코드 실행 결과](./execution.md)

---
# 2주차
## _구현 기능_
### spring 의존성 추가

`spring`, `spring-boot`, `lombok`, `spring web`,
그리고 `jpa`
(jpa는 이번 미션에서 사용하지 않지만 이후 과제를 위한 세팅)  
관련 의존성을 `build.gradle`파일에 추가하였음.

### 상황 설정

포스트맨으로 게임 진행.

### 게임 시작
시작과 동시에 두 플레이어, 스킬을 포함한 행동 리스트가 세팅이 된다.  

1주차에는 각 플레이어가 고유의 행동을 가질 수 있도록 플레이어 별로 행동을 부여하는 식으로 설계했지만,  
이번에는 ActionRepository에 메모리 저장 형식으로 행동 리스트를 공유하도록 하였다.

### 게임 진행
게임 진행은 포스트맨에서 request를 보냄으로써 진행된다.
action을 request로 보냄으로써 한 플레이어의 한 턴이 시작되는데,
진행 조건에 부합한다면 입력받은 플레이어 행동을 개시한다.

### 게임 끝
게임 진행 시에 보내는 요청과 동일하다.
다만 action을 request로 보냈을 때
만약 끝난 게임이라면 게임 결과를 response로 보낸다.

#### @참고
커스텀 예외 클래스, 에러 메세지 상수화 등  
예외 처리 구현은 다음 주차에 구현 예정.

### 패키지 소개

1. player 패키지
- 플레이어 정보에 관한 내용을 담았다.
- 엔티티, 레포지토리, 서비스 계층을 구현하였다.
- 게임 시작 전, 두 플레이어의 정보를 레포지토리 계층에 저장한다.
2. manager 패키지
- 게임 진행 정보를 담았다.
- 컨트롤러와 서비스 계층을 구현하였다.
- 레포지토리 대신, 저장해야 할 maxTurn(최대 턴수)를 포함한 게임 진행에 필요한 환경을 서비스 계층에 구현하였다.
3. action 패키지
- 모든 행동에 관한 내용을 담았다.
- 엔티티, 레포지토리, 서비스 계층을 구현하였다.
- 레포지토리 계층에 두 플레이어가 공유할 행

---
# 3주차
## 수정 사항
### interface
interface : only for 다른 객체로부터 요청받은 책임 나열

따라서 객체 내부에서의 작업은 interface 내부에 포함할 필요가 없음.

### getter, setter 정리
a의 책임을 b에서 지고 있지 않은지 검토 및 수정
getter setter 메서드 최소화

### abstract vs. interface
- 전체적인 객체 설계는 interface
- 구현하면서 중복되는 코드는 abstract

## _구현 기능_






