--Скрипт с созданием View для получения встреч с количеством подтвержденных заявок больше 0.
DROP VIEW IF EXISTS confirmed_app;
CREATE VIEW confirmed_app as
SELECT meeting_id, count(*) as count
FROM meetings_users
WHERE user_status = true
GROUP BY meeting_id;

--Получение списка всех встреч с количеством подтвердивших участников
SELECT m.name meeting_name, COALESCE(ca.count, 0) app_number
FROM meetings m LEFT JOIN confirmed_app ca
ON m.id = ca.meeting_id;

--Получение списка встреч, на которые не было подано ни одной заявки
SELECT m.name meeting_name
FROM meetings m LEFT JOIN confirmed_app ca
ON m.id = ca.meeting_id
WHERE ca.meeting_id ISNULL;