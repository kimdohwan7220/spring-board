<h1>📘 Spring + React 게시판 프로젝트 - 백엔드</h1>

<p>
이 프로젝트는 <strong>Spring Boot</strong>와 <strong>React</strong>로 구성된 풀스택 게시판 서비스입니다.<br>
회원가입, 로그인, 게시물 CRUD, 댓글, 좋아요, 프로필 이미지 업로드 등을 지원합니다.
</p>

<hr>

<h2>📊 ERD (Entity Relationship Diagram)</h2>

![ERD](https://github.com/user-attachments/assets/f0f389c6-8173-4097-b6e5-018856929009)


<hr>

<h2>🛠️ 기술 스택</h2>

<h3>Backend</h3>
<ul>
  <li>Java 17</li>
  <li>Spring Boot</li>
  <li>Spring Web</li>
  <li>Spring Data JPA</li>
  <li>MySQL</li>
  <li>Hibernate</li>
  <li>Validation</li>
</ul>

<h3>Frontend</h3>
<ul>
  <li>React (Vite)</li>
  <li>React Router</li>
  <li>Axios</li>
</ul>

<h3>Infra</h3>
<ul>
  <li>MySQL 8</li>
  <li>Docker </li>
</ul>

<hr>

<h2>🗂️ Backend 프로젝트 구조</h2>

<pre>
src/main/java/com.project.main
 ├── config
 ├── controller
 ├── domain
 ├── repository
 ├── service
 └── dto
</pre>
<h2>📡 주요 API 요약</h2>

<h3>🔐 Auth (회원 관련)</h3>
<table>
<thead>
<tr><th>기능</th><th>Method</th><th>Endpoint</th></tr>
</thead>
<tbody>
<tr><td>회원가입</td><td>POST</td><td>/auth/register</td></tr>
<tr><td>로그인</td><td>POST</td><td>/auth/login</td></tr>
<tr><td>사용자 조회</td><td>GET</td><td>/auth/user</td></tr>
<tr><td>프로필 이미지 업로드</td><td>POST</td><td>/users/profile-image</td></tr>
<tr><td>비밀번호 변경</td><td>PUT</td><td>/users/password</td></tr>
<tr><td>닉네임 변경</td><td>PUT</td><td>/users/username</td></tr>
</tbody>
</table>

<h3>📝 Post (게시물)</h3>
<table>
<thead>
<tr><th>기능</th><th>Method</th><th>Endpoint</th></tr>
</thead>
<tbody>
<tr><td>게시글 작성</td><td>POST</td><td>/posts</td></tr>
<tr><td>게시글 목록</td><td>GET</td><td>/posts</td></tr>
<tr><td>게시글 상세 조회</td><td>GET</td><td>/posts/{id}</td></tr>
<tr><td>게시글 수정</td><td>PUT</td><td>/posts/{id}</td></tr>
<tr><td>게시글 삭제</td><td>DELETE</td><td>/posts/{id}</td></tr>
<tr><td>조회수 증가</td><td>POST</td><td>/posts/{id}/views</td></tr>
<tr><td>좋아요 토글</td><td>POST</td><td>/posts/{id}/like</td></tr>
</tbody>
</table>

<h3>💬 Comment (댓글)</h3>
<table>
<thead>
<tr><th>기능</th><th>Method</th><th>Endpoint</th></tr>
</thead>
<tbody>
<tr><td>댓글 작성</td><td>POST</td><td>/posts/{postId}/comments</td></tr>
<tr><td>댓글 조회</td><td>GET</td><td>/posts/{postId}/comments</td></tr>
<tr><td>댓글 수정</td><td>PUT</td><td>/posts/{postId}/comments/{commentId}</td></tr>
<tr><td>댓글 삭제</td><td>DELETE</td><td>/posts/{postId}/comments/{commentId}</td></tr>
</tbody>
</table>

<hr>