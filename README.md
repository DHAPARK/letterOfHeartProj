# letterOfHeartProj
크리스마스 , 설날 ,추석, 스승의날 등등.. 축하하거나 경사스러운 날에 마음을 전하는 편지

김이영 참여
박도현 부캐 참여
최현석 참여
박기쁜 참여
이슬기 참여

##DB / JPA
1. 기능 -  회원가입, 로그인, 트리공유, 트리메세지

[User]</br>
String nickname; - not null, UK // 닉네임</br>
String userid;   - not null, PK // 아이디</br>
String userpw;   - not null     // 비밀번호</br>
</br>
[tree]  // 트리는 회원가입 한사람만 가짐 / 매년 트리를 만들 수 있는지? </br>
Long treeNum; PK  // 트리번호</br>
String userid(fK) // 아이디</br>
Long messageCnt; // 메세지 개수</br>
LocalDateTime createDate; // 생성일자</br>
</br>

[message] 
Long num; - 메세지번호(시퀀스) - PK
Long treeNum (fk) - 트리번호
String userid // 메시지 받는사람
String senderNickname;  // 보내는 사람 닉네임
String contents;  // 내용
LocalDateTime sendDate; // 메세지 작성일자
LocalDateTime openedDate; // 메세지 확인 날짜
Long status;  // 메시지를 열었는지, 안열었는지 상태값 


[messageDeco]
Long messageNum; (fk) 메세지번호
Long ornamentType; // 트리장식
Long ornamentColor; // 장식 색상
Long ornamentpattern; // 장식 패턴
Long boxColor;   // 박스색상
Long ribbonColor;  // 박스무늬

권혁주 참여
