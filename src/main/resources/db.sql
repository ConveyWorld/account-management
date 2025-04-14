
CREATE TABLE ACCOUNT (
                         ACCOUNT_ID      VARCHAR2(50) PRIMARY KEY,
                         NAME            VARCHAR2(100),
                         ACCOUNT_TYPE    VARCHAR2(50),
                         BALANCE         NUMBER(18, 2),
                         PHONE_NUMBER    VARCHAR2(20),
                         EMAIL           VARCHAR2(100),
                         ADDRESS         VARCHAR2(255),
                         ACCOUNT_STATUS  VARCHAR2(20)
);


INSERT INTO ACCOUNT (ACCOUNT_ID, NAME, ACCOUNT_TYPE, BALANCE, PHONE_NUMBER, EMAIL, ADDRESS, ACCOUNT_STATUS)
VALUES ('ACC001', 'Nguyen Van A', 'SAVINGS', 1500000.00, '0909123456', 'a.nguyen@example.com', '123 Lê Lợi, Q1, TP.HCM', 'Y');

SELECT * FROM ACCOUNT a