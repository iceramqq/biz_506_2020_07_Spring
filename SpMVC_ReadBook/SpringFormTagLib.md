# spring form taglib
* spring project 에서 사용할 수 잇는 jsp 확장 taglib이다
* form taglib를 사용하면 controller의 model과 연동하여 input 관련 form을 비교적 쉽게
구현할 수 있도록 한다.

## form taglib 프로젝트 작성 순서
0. http://www.springframework.org/tags/form 를 taglib로 설정
1. form 에서 input box를 구현하고
2. input box의 name 값과 같은 vo 클래스를 작성한다
3. form의 form tag를 form:form으로, input tag를 for:input으로
4. input tag의 id 속성을 모두 제거, name 속성을 apth 속성으로 변경
5. input tag는 반드시 self closing 하여준다. : <form:input path="" />
6. form:form tag method를 제거해도 된다. : 기본값이 method=POST로 설정된다
7. action부분을 필요에 따라 설정
8. modelAttribute에 controller에서 model에 실어서 보내는 vo 이름을 작성해야 한다