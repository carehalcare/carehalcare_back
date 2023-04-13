INSERT INTO USER_TABLE (BIRTH_DATE, CODE, CUSER_ID, PASSWORD, PHONE, PUSER_ID, SEX, USER_ID, USERNAME)
 VALUES ('1999-08-02', 1, '', 'password1', '010-0000-0000', '', 'F', 'userid1', '보호자'),
    ('1998-01-15', 0, '', 'password2', '010-1111-0000', '', 'M', 'userid2', '간병인');


INSERT INTO NOTICE VALUES (1, '2023-04-03','식단에 단백질을 꼭 넣어주세요.', 'userid1');
INSERT INTO NOTICE VALUES (2, '2023-04-13','복용해아 하는 약이 추가되었습니다. 확인 부탁드립니다.', 'userid1');
INSERT INTO NOTICE VALUES (3, '2023-04-15','오늘 청소는 안 하셔도 됩니다.', 'userid1');

INSERT INTO PATIENT_INFO VALUES (1, '2023-04-03','치매 초기', '순천향병원', '갈란타민, 메만틴', '1958-01-15', '이주연','M', '잠자리에 들 때 주변 작은 소음에도 민감합니다.', 'userid1');