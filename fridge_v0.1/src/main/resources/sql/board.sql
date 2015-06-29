--자유 게시판 테이블 생성--
drop table board;
delete from board;
create table board(
   board_no         number               primary key,
   category			varchar2(50)		not null,
   title               varchar2(50)      not null,
   contents         clob    not null,
   post_date         date      not null,
   nick               varchar2(50)      not null,
   hits               number             default 0,
   member_id      varchar2(50)      not null,
   constraint fk_memberId foreign key(member_id) references member(member_id)
)
create sequence board_seq nocache;
	
--자유 게시판 페이징--
	select board_no,category,nick,title,post_date,hits from(
	select board_no,category,nick,title,post_date,hits,ceil(rownum/5) as page from(
		select board_no,category,nick,title,to_char(post_date,'YYYY.MM.DD') as post_date,hits
		from board order by board_no desc
		)
	) where page=1
	
--자유게시판 첨부파일 테이블 생성--
create table board_file(
	file_no number primary key,
	file_name varchar2(100) not null,
	board_no number not null,
	file_path varchar2(500) not null,
	constraint fk_board_no_file foreign key(board_no) references board(board_no)
)

