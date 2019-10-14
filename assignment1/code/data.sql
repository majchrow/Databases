-- Dane

INSERT INTO osoby (imie, nazwisko, pesel, kontakt)
VALUES('Adam', 'Kowalski', '87654321', 'tel: 6623');

INSERT INTO osoby (imie, nazwisko, pesel, kontakt)
VALUES('Jan', 'Nowak', '12345678', 'tel: 2312');

INSERT INTO osoby (imie, nazwisko, pesel, kontakt)
VALUES('Jan', 'Towas', '12345679', 'tel: 5123');

INSERT INTO osoby (imie, nazwisko, pesel, kontakt)
VALUES('Damian', 'Pokes', '92343678', 'tel: 1212');

INSERT INTO osoby (imie, nazwisko, pesel, kontakt)
VALUES('Artur', 'Boruc', '72343678', 'tel: 2345');

INSERT INTO osoby (imie, nazwisko, pesel, kontakt)
VALUES('Robert', 'Lewandowski', '72343671', 'tel: 1331');

INSERT INTO osoby (imie, nazwisko, pesel, kontakt)
VALUES('Marcin', 'Gortat', '12121212', 'tel: 1111');

INSERT INTO osoby (imie, nazwisko, pesel, kontakt)
VALUES('Robert', 'Kubica', '42143121', 'tel: 3333');

INSERT INTO osoby (imie, nazwisko, pesel, kontakt)
VALUES('Jan', 'Kowalski', '72343671', 'tel: 1666');

INSERT INTO osoby (imie, nazwisko, pesel, kontakt)
VALUES('Janusz', 'Borek', '12312314', 'tel: 5331');

INSERT INTO wycieczki (nazwa, kraj, data, opis, liczba_miejsc)
VALUES ('Wycieczka do Paryza','Francja',TO_DATE('2016-01-01','YYYY-MM-DD'),'Ciekawa wycieczka ...',3);

INSERT INTO wycieczki (nazwa, kraj, data, opis, liczba_miejsc)
VALUES ('Piękny Kraków','Polska',TO_DATE('2017-02-03','YYYY-MM-DD'),'Najciekawa wycieczka ...',2);

INSERT INTO wycieczki (nazwa, kraj, data, opis, liczba_miejsc)
VALUES ('Wieliczka','Polska',TO_DATE('2017-03-03','YYYY-MM-DD'),'Zadziwiająca kopalnia ...',2);

INSERT INTO wycieczki (nazwa, kraj, data, opis, liczba_miejsc)
VALUES ('Energylandia','Polska',TO_DATE('2019-10-28','YYYY-MM-DD'),'Zadziwiający park rozrywki ...',12);

INSERT INTO rezerwacje(id_wycieczki, id_osoby, status)
VALUES (1,1,'N');

INSERT INTO rezerwacje(id_wycieczki, id_osoby, status)
VALUES (2,2,'P');

INSERT INTO rezerwacje(id_wycieczki, id_osoby, status)
VALUES (3,3,'Z');

INSERT INTO rezerwacje(id_wycieczki, id_osoby, status)
VALUES (4,4,'Z');

INSERT INTO rezerwacje(id_wycieczki, id_osoby, status)
VALUES (4,5,'A');

INSERT INTO rezerwacje(id_wycieczki, id_osoby, status)
VALUES (3,6,'P');

INSERT INTO rezerwacje(id_wycieczki, id_osoby, status)
VALUES (4,7,'N');

INSERT INTO rezerwacje(id_wycieczki, id_osoby, status)
VALUES (1,8,'A');

INSERT INTO rezerwacje(id_wycieczki, id_osoby, status)
VALUES (2,9,'N');

INSERT INTO rezerwacje(id_wycieczki, id_osoby, status)
VALUES (4,10,'Z');
