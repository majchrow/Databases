-- Procedury

-- Dodaj rezerwacje
CREATE OR REPLACE PROCEDURE DODAJ_REZERWACJE(ID_WYC INT, ID_OS INT) AS
    counter INT;
    not_exists EXCEPTION;
    already_exists EXCEPTION;
BEGIN
    SAVEPOINT DODAJ_REZERWACJE_SAVEPOINT;
    SELECT COUNT(*) INTO counter FROM OSOBY o WHERE o.ID_OSOBY = ID_OS;
    IF counter = 0 THEN
        RAISE not_exists;
    END IF;

    SELECT COUNT(*) INTO counter FROM dostepne_wycieczki w WHERE w.ID_WYCIECZKI = ID_WYC;
    IF counter = 0
    THEN
        RAISE not_exists;
    END IF;

    SELECT COUNT(*) INTO counter FROM REZERWACJE r WHERE r.ID_WYCIECZKI = ID_WYC AND r.ID_OSOBY = ID_OS;
    IF counter > 0
    THEN
        RAISE already_exists;
    END IF;

    INSERT INTO REZERWACJE (id_wycieczki, id_osoby, STATUS)
    VALUES (ID_WYC, ID_OS, 'N');
    COMMIT;

    EXCEPTION
      WHEN OTHERS THEN
      ROLLBACK TO DODAJ_REZERWACJE_SAVEPOINT;
      RAISE;
END DODAJ_REZERWACJE;

-- Zmień status rezerwacji
CREATE OR REPLACE PROCEDURE ZMIEN_STATUS_REZERWACJI(ID_REZ INT, NOWY_STATUS CHAR) AS
    counter INT;
    stary_status CHAR;
    not_exists EXCEPTION;
    wrong_status EXCEPTION;
BEGIN
    SAVEPOINT ZMIEN_STATUS_REZERWACJI_SAVEPOINT;

    SELECT COUNT(*) INTO counter FROM REZERWACJE r WHERE r.NR_REZERWACJI = ID_REZ;
    IF counter = 0 THEN
        RAISE not_exists;
    END IF;

    SELECT COUNT(*) INTO counter FROM REZERWACJE r
           JOIN DOSTEPNE_WYCIECZKI DW on r.ID_WYCIECZKI = DW.ID_WYCIECZKI
        WHERE r.NR_REZERWACJI = ID_REZ;
    IF counter = 0 THEN
       RAISE not_exists; -- Only future trips
    END IF;

    SELECT STATUS into stary_status FROM REZERWACJE r WHERE r.NR_REZERWACJI = ID_REZ;

    IF stary_status='A' THEN
       SELECT DW.LICZBA_WOLNYCH_MIEJSC INTO counter FROM REZERWACJE r
           JOIN DOSTEPNE_WYCIECZKI DW on r.ID_WYCIECZKI = DW.ID_WYCIECZKI
        WHERE r.NR_REZERWACJI = ID_REZ;
        IF counter < 1 THEN
           RAISE wrong_status; -- No seats left for this reservation
        END IF;
    END IF;

    UPDATE REZERWACJE R
    SET R.STATUS = NOWY_STATUS
    WHERE R.NR_REZERWACJI = ID_REZ;
    COMMIT;

    EXCEPTION
      WHEN OTHERS THEN
      ROLLBACK TO ZMIEN_STATUS_REZERWACJI_SAVEPOINT;
      RAISE;

END ZMIEN_STATUS_REZERWACJI;

-- Zmień liczbę miejsc
CREATE OR REPLACE PROCEDURE ZMIEN_LICZBE_MIEJSC(ID_WYC INT, NOWA_LICZBA_MIEJSC INT) AS
    counter INT;
    not_exists EXCEPTION;
    wrong_size EXCEPTION;
BEGIN
    SAVEPOINT ZMIEN_LICZBE_MIEJSC_SAVEPOINT;

    SELECT COUNT(*) INTO counter FROM DOSTEPNE_WYCIECZKI w WHERE w.ID_WYCIECZKI = ID_WYC;

    IF counter = 0 THEN
        RAISE not_exists;
    END IF;

    SELECT w.LICZBA_MIEJSC - w.LICZBA_WOLNYCH_MIEJSC INTO counter FROM DOSTEPNE_WYCIECZKI w WHERE w.ID_WYCIECZKI = ID_WYC; -- number of taken seats

    IF counter > NOWA_LICZBA_MIEJSC THEN -- number of taken seats should be <= new number of seats
        RAISE wrong_size; -- Not enough seats for existing reservations.
    END IF;

    UPDATE WYCIECZKI w
    SET w.LICZBA_MIEJSC = NOWA_LICZBA_MIEJSC
    WHERE w.ID_WYCIECZKI = ID_WYC;
    COMMIT;

    EXCEPTION
      WHEN OTHERS THEN
      ROLLBACK TO ZMIEN_LICZBE_MIEJSC_SAVEPOINT;
      RAISE;

END ZMIEN_LICZBE_MIEJSC;

-- Z tabelą dzennikującą

-- Dodaj rezerwacje
CREATE OR REPLACE PROCEDURE dodaj_rezerwacje(ID_WYC INT, ID_OS INT) AS
    counter INT;
    not_exists EXCEPTION;
    already_exists EXCEPTION;
BEGIN
    SAVEPOINT dodaj_rezerwacje_savepoint;
    SELECT COUNT(*) INTO counter FROM OSOBY WHERE OSOBY.ID_OSOBY = ID_OS;
    IF counter = 0 THEN
        raise not_exists;
    END IF;

    SELECT COUNT(*) INTO counter FROM dostepne_wycieczki w WHERE w.ID_WYCIECZKI = ID_WYC;
    IF counter = 0
    THEN
        raise not_exists; -- No seats for given trip
    END IF;

    SELECT COUNT(*) INTO counter FROM REZERWACJE r WHERE r.ID_WYCIECZKI = ID_WYC AND r.ID_OSOBY = ID_OS;
    IF counter > 0
    THEN
        raise already_exists;
    END IF;

    INSERT INTO REZERWACJE (id_wycieczki, id_osoby, STATUS)
    VALUES (ID_WYC, ID_OS, 'N');

    SELECT r.NR_REZERWACJI INTO counter FROM REZERWACJE r WHERE r.ID_WYCIECZKI = ID_WYC AND r.ID_OSOBY=ID_OS;
    INSERT INTO REZERWACJE_LOG (id_rezerwacji, data, status)
    VALUES (counter, CURRENT_DATE, 'N');
    COMMIT;

    EXCEPTION
      WHEN OTHERS THEN
      ROLLBACK TO dodaj_rezerwacje_savepoint;
      RAISE;
END dodaj_rezerwacje;


-- Zmień status rezerwacji
CREATE OR REPLACE PROCEDURE ZMIEN_STATUS_REZERWACJI(ID_REZ INT, NOWY_STATUS CHAR) AS
    counter INT;
    stary_status CHAR;
    not_exists EXCEPTION;
    wrong_status EXCEPTION;
BEGIN
    SAVEPOINT ZMIEN_STATUS_REZERWACJI_SAVEPOINT;

    SELECT COUNT(*) INTO counter FROM REZERWACJE r WHERE r.NR_REZERWACJI = ID_REZ;
    IF counter = 0 THEN
        RAISE not_exists;
    END IF;

    SELECT COUNT(*) INTO counter FROM REZERWACJE r
           JOIN DOSTEPNE_WYCIECZKI DW on r.ID_WYCIECZKI = DW.ID_WYCIECZKI
        WHERE r.NR_REZERWACJI = ID_REZ;
    IF counter = 0 THEN
       RAISE not_exists; -- Only future trips
    END IF;

    SELECT STATUS into stary_status FROM REZERWACJE r WHERE r.NR_REZERWACJI = ID_REZ;

    IF stary_status='A' THEN
       SELECT DW.LICZBA_WOLNYCH_MIEJSC INTO counter FROM REZERWACJE r
           JOIN DOSTEPNE_WYCIECZKI DW on r.ID_WYCIECZKI = DW.ID_WYCIECZKI
        WHERE r.NR_REZERWACJI = ID_REZ;
        IF counter < 1 THEN
           RAISE wrong_status; -- No seats left for this reservation
        END IF;
    END IF;

    UPDATE REZERWACJE R
    SET R.STATUS = NOWY_STATUS
    WHERE R.NR_REZERWACJI = ID_REZ;

    INSERT INTO REZERWACJE_LOG (id_rezerwacji, data, status)
    VALUES (ID_REZ, CURRENT_DATE, NOWY_STATUS);
    COMMIT;

    EXCEPTION
      WHEN OTHERS THEN
      ROLLBACK TO ZMIEN_STATUS_REZERWACJI_SAVEPOINT;
      RAISE;

END ZMIEN_STATUS_REZERWACJI;