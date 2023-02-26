create table SAMPLE_SPRING_DATA
(
    ID             NUMBER generated as identity
        primary key,
    FIRST_NAME     VARCHAR2(50),
    LAST_NAME      VARCHAR2(100),
    TODAY_DATE     DATE,
    TODAY_DATETIME TIMESTAMP(6),
    IS_ACTIVE      CHAR,
    RANDOM_NUMBER  NUMBER
)