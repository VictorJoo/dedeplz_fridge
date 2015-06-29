-- 회원 관리 테이블 생성--
select * from member;
drop table member;
create table member(
	member_id varchar2(50) primary key,
	password varchar2(50) not null,
	nick varchar2(50) not null,
	name varchar2(50) not null,
	email varchar2(50) not null,
	gender number default 0,
	answer varchar2(50) not null,
	member_level number default 0,
	register_date date not null,
	love number default 0
)

   