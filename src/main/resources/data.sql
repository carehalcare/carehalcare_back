INSERT INTO USER_TABLE (BIRTH_DATE, CODE, CUSER_ID, PASSWORD, PHONE, PUSER_ID, SEX, USER_ID, USERNAME)
 VALUES ('1999-08-02', 1, '', 'password1', '010-0000-0000', '', 'F', 'userid1', '보호자'),
    ('1998-01-15', 0, '', 'password2', '010-1111-0000', '', 'M', 'userid2', '간병인');


INSERT INTO NOTICE VALUES (1, '2023-04-03','공지사항 내용', 'userid1');

INSERT INTO PATIENT_INFO VALUES (1, '2023-04-03','질병', '병원', '약', '생년월일', '환자이름','F', '특이사항', 'userid1');