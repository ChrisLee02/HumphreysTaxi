UNIT, Address가 OPSEC 위반일 수 있어서
고민 좀 해봐야 할 듯...?

## *회원가입 / 아이디중복확인 / 로그인*  // 회원탈퇴는 좀 더 알아보고

### post /auth/sign-up

#### *request

id: string <br/>
unit: string <br/>
username: string <br/>
password: string <br/>
address: string <br/>
defaultDepartingPoint: string <br/>

#### *response

token: string <br/>
expirationTime: string <br/>

### post /auth/sign-in

#### *request

id: string <br>
password: string

#### *response

token: string <br/>
expirationTime: string <br/>

### post /auth/id-check

#### *request

id:string

#### *response

default

========================================

## *board*

Board get에는 제약 X

Comment에서 Forbidden까지 적용시킨다.

### get /board/boards

#### *request

none

#### *response

boardList: boardListItem[]

### get /board/{boardId}

#### *request

none

#### *response

boardId: int <br>
title: String <br>
content: String <br>
startingPoint: String <br>
destinationPoint: String <br>
departTime: String <br>

### delete /board/{boardId}

#### *request

none

#### *response

default

### patch /board/{boardId} -> 서비스 상 구현해도 되는지는 의문.

#### *request

title: String <br>
content: String <br>
startingPoint: String <br>
destinationPoint: String <br>
departTime: String <br>

#### *response

default

### post /board

#### *request

title: String <br>
content: String <br>
startingPoint: String <br>
destinationPoint: String <br>
departTime: String <br>

#### *response

id: Integer <br/>

### put /board/{boardId}/join -> 해당 게시글에 참여/취소

#### *request

none

#### *response

default

### get /board/{boardId}/join -> 해당 게시글 참여자 리스트

#### *request

none

#### *response

joiningUserList: JoiningUserListItem[] <br/>

### get, post, delete, patch(고민) /board/{boardId}/comment -> 댓글

#### *request (post, patch 한정)

content: string <br/>

#### *response

default

========================================

## *user*

### get /user/{userId}

#### *request

none

#### *response

id: string <br>
unit: string <br>
username: string <br>
profile_image: string <br>
address: string <br>
defaultDepartingPoint: string <br>
penalty_until: string <br>

### patch /user/{userId}

#### *request

unit: string <br>
username: string <br>
profile_image: string <br>
address: string <br>
defaultDepartingPoint: string <br>

#### *response

default

