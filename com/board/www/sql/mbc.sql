CREATE TABLE BOARD (
	BNO			NUMBER(5) PRIMARY KEY,
	BTITLE		NVARCHAR2(30) NOT NULL,
	BCONTENTS	NVARCHAR2(1000) NOT NULL,
	BWRITER		NVARCHAR2(10) NOT NULL,
	BDATE		DATE NOT NULL );

CREATE SEQUENCE BOARD_SEQ
	INCREMENT BY 1
	START WITH 1
	NOCYCLE
	NOCACHE ;
	
INSERT INTO BOARD VALUES(board_seq.nextval, '비오내요~', '비오는데 등교하시는냐고 고생 하셨습니다.', '김기원', sysdate);
INSERT INTO BOARD VALUES(board_seq.nextval, '안녕하세요~', '비오는데 등교하시는냐고 고생 하셨습니다.', '김기원', sysdate);
INSERT INTO BOARD VALUES(board_seq.nextval, '감사합니다.~', '비오는데 등교하시는냐고 고생 하셨습니다.', '김기원', sysdate);
INSERT INTO BOARD VALUES(board_seq.nextval, '수고하셨내요~', '비오는데 등교하시는냐고 고생 하셨습니다.', '김기원', sysdate);
INSERT INTO BOARD VALUES(board_seq.nextval, '화이팅하세요~', '비오는데 등교하시는냐고 고생 하셨습니다.', '김기원', sysdate);
INSERT INTO BOARD VALUES(board_seq.nextval, '방갑습니다.~', '비오는데 등교하시는냐고 고생 하셨습니다.', '김기원', sysdate);

SELECT * FROM BOARD;

-- MEMBER 테이블 용

CREATE TABLE MEMBER (
	MNO			NUMBER(5) PRIMARY KEY,
	MID			NVARCHAR2(10) NOT NULL,
	MPW			NVARCHAR2(10) NOT NULL,
	MDATE		DATE NOT NULL
);

-- 더미데이터
INSERT INTO MEMBER VALUES(board_seq.nextval, '김기원', '1234', sysdate);
INSERT INTO MEMBER VALUES(board_seq.nextval, '안희준', '1234', sysdate);
INSERT INTO MEMBER VALUES(board_seq.nextval, '조건재', '1234', sysdate);
INSERT INTO MEMBER VALUES(board_seq.nextval, '양승환', '1234', sysdate);
INSERT INTO MEMBER VALUES(board_seq.nextval, '용상엽', '1234', sysdate);

SELECT * FROM MEMBER;

	