-- 레시피 테이블 --
drop table recipe;
drop table recipe_file;
drop table recipe_item;
select * from recipe;
create table recipe(
   recipe_no         number               primary key,
   title               varchar2(50)      not null,
   contents         clob    not null,
   post_date         date      not null,
   nick               varchar2(50)      not null,
   cooking_time   number               not null,
   hits               number             default 0,
   member_id      varchar2(50)      not null,
   constraint fk_member_id foreign key(member_id) references member(member_id)
)
drop sequence recipe_seq;
create sequence recipe_seq nocache; 
delete from recipe;

-- 요리재료 테이블 -- 
drop table item;
create table item(
	item_no number primary key,
	item_name varchar2(50) not null
)
drop sequence item_no_seq;
create sequence item_no_seq nocache;

-- 식자재 테이블 --
select * from recipe_item;
drop table recipe_item;
create table recipe_item(
	food_reserves number primary key,
	item_no number not null,
	recipe_no number not null,
	constraint fk_recipe_no foreign key(recipe_no) references recipe(recipe_no),
	constraint fk_item_no foreign key(item_no) references item(item_no)
)
drop sequence food_reserves_seq;
create sequence food_reserves_seq nocache;
commit

-- 레시피 번호로 재료 목록 뽑기 -- 
select item_name from item i, 
(select item_no from recipe_item where recipe_no=2) 
where i.item_no=r.item_no;

-- 재료로 레시피 찾기 -- 
select * from recipe 
where recipe_no=(select recipe_no from recipe_item 
where item_no=(select item_no from item 
where item_name='날치알'));

delete from recipe_item;
delete from recipe_file;
delete from recipe;
select * from recipe;
select * from recipe_item;
select * from item;
drop table recipe_file
create table recipe_file(
	file_no number primary key,
	file_name varchar2(100) not null,
	recipe_no number not null,
	file_path varchar2(500) not null,
	constraint fk_recipe_no_file foreign key(recipe_no) references recipe(recipe_no)
)
create sequence file_no_seq nocache;
drop sequence file_no_seq;

--좋아요 싫어요 테이블--
create table good_n_bad(
   gnb_no number primary key,
   good number default 0,
   bad number default 0,
   recipe_no number not null,
   member_id varchar2(50) not null,
   constraint fk_recipe_no_gnb foreign key(recipe_no) references recipe(recipe_no),
   constraint fk_member_id_gnb foreign key(member_id) references member(member_id)
);
create sequence gnb_no_seq nocache;

--즐겨 찾기 테이블 생성--
drop table favorites;
create table favorites(
   favorites_no         number               primary key,
   member_id           varchar2(50)         not null,
   recipe_no             number             not null,
   insert_date         date                  not null,
   constraint recipe_no   foreign key(recipe_no) references recipe(recipe_no),
   constraint member_id   foreign key(member_id) references member(member_id)
)
drop sequence favorites_no_seq;
create sequence favorites_no_seq nocache;
select * from favorites;

-- 인기도 탑 3 --
select count(good) point from good_n_bad where recipe_no=11 and good=1;
select recipe_no from recipe where 
select r.recipe_no from recipe r , good_n_bad gb
select recipe_no from good_n_bad where order by point desc 
select *, 

-- 레시피의 댓글 테이블 생성 sql -- 
create table recipe_comment(
	comment_no  number  primary key,
	comment_group  number   not null,
	comment_level  number  default 0,
	comment_contents  clob   not null,
	comment_time date  not null,
	comment_nick   varchar2(30)   not null,
	comment_recipe_no number   not null ,
	comment_ref_nick varchar2(30) not null
)
drop table recipe_comment
create sequence recipe_comment_seq nocache;
drop sequence recipe_comment_seq;
select * from recipe_comment;

select recipe_no from recipe where member_id='java';
