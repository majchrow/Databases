-- Widoki

-- Wycieczki osoby
CREATE OR REPLACE VIEW WYCIECZKI_OSOBY
AS
SELECT
o.ID_OSOBY,
w.ID_WYCIECZKI,
w.NAZWA,
w.KRAJ,
w.DATA,
o.IMIE,
o.NAZWISKO,
r.STATUS
FROM WYCIECZKI w
JOIN REZERWACJE r ON w.ID_WYCIECZKI = r.ID_WYCIECZKI
JOIN OSOBY o ON r.ID_OSOBY = o.ID_OSOBY;

-- Wycieczki osoby potwierdzone
CREATE OR REPLACE VIEW WYCIECZKI_OSOBY_POTWIERDZONE
AS
SELECT
w.ID_WYCIECZKI,
w.NAZWA,
w.KRAJ,
w.DATA,
o.IMIE,
o.NAZWISKO,
r.STATUS
FROM WYCIECZKI w
JOIN REZERWACJE r ON w.ID_WYCIECZKI = r.ID_WYCIECZKI
JOIN OSOBY o ON r.ID_OSOBY = o.ID_OSOBY
WHERE r.STATUS IN ('P', 'Z');

-- Wycieczki przyszłe
CREATE OR REPLACE VIEW WYCIECZKI_PRZYSZLE
AS
SELECT
w.ID_WYCIECZKI,
w.NAZWA,
w.KRAJ,
w.DATA,
o.IMIE,
o.NAZWISKO,
r.STATUS
FROM WYCIECZKI w
JOIN REZERWACJE r ON w.ID_WYCIECZKI = r.ID_WYCIECZKI
JOIN OSOBY o ON r.ID_OSOBY = o.ID_OSOBY
WHERE w.DATA > CURRENT_DATE AND r.STATUS <> 'A';

-- Wycieczki miejsca
CREATE OR REPLACE VIEW WYCIECZKI_MIEJSCA
AS
SELECT
w.ID_WYCIECZKI,
w.KRAJ,
w.DATA,
w.NAZWA,
w.OPIS,
w.LICZBA_MIEJSC,
w.LICZBA_MIEJSC - (SELECT COUNT(*) FROM REZERWACJE r WHERE w.ID_WYCIECZKI = r.ID_WYCIECZKI AND r.STATUS <> 'A') AS LICZBA_WOLNYCH_MIEJSC
FROM WYCIECZKI w;

-- Dostępne wycieczki
CREATE OR REPLACE VIEW DOSTEPNE_WYCIECZKI
AS
SELECT
w.ID_WYCIECZKI,
w.KRAJ,
w.DATA,
w.NAZWA,
w.OPIS,
w.LICZBA_MIEJSC,
w.LICZBA_WOLNYCH_MIEJSC
FROM WYCIECZKI_MIEJSCA w
WHERE LICZBA_WOLNYCH_MIEJSC > 0 AND w.DATA > CURRENT_DATE;

-- Rezerwacje do anulowania
CREATE OR REPLACE VIEW REZERWACJE_DO_ANULOWANIA
AS
SELECT
r.NR_REZERWACJI,
w.ID_WYCIECZKI,
w.NAZWA,
w.KRAJ,
w.DATA,
r.STATUS
FROM REZERWACJE r
JOIN WYCIECZKI W ON r.ID_WYCIECZKI = W.ID_WYCIECZKI
WHERE  r.STATUS = 'N' AND w.DATA BETWEEN CURRENT_DATE AND CURRENT_DATE+7;

-- Po dodaniu pola do wycieczek

-- Wycieczki miejsca 2
CREATE OR REPLACE VIEW WYCIECZKI_MIEJSCA_2
AS
SELECT
w.ID_WYCIECZKI,
w.KRAJ,
w.DATA,
w.NAZWA,
w.OPIS,
w.LICZBA_MIEJSC,
w.LICZBA_WOLNYCH_MIEJSC
FROM WYCIECZKI w;

-- Dostępne wycieczki 2
CREATE OR REPLACE VIEW DOSTEPNE_WYCIECZKI_2
AS
SELECT
w.ID_WYCIECZKI,
w.KRAJ,
w.DATA,
w.NAZWA,
w.OPIS,
w.LICZBA_MIEJSC,
w.LICZBA_WOLNYCH_MIEJSC
FROM WYCIECZKI w
WHERE LICZBA_WOLNYCH_MIEJSC > 0 AND w.DATA > CURRENT_DATE;
