-- 코드를 작성해주세요
SELECT fi.ID AS ID, fn.FISH_NAME AS FISH_NAME, fi.LENGTH AS LENGTH
FROM FISH_INFO fi, FISH_NAME_INFO fn
WHERE fi.FISH_TYPE = fn.FISH_TYPE
    AND (fi.FISH_TYPE, FI.LENGTH) IN (SELECT FISH_TYPE, MAX(LENGTH)
                                     FROM FISH_INFO
                                     GROUP BY FISH_TYPE)
ORDER BY fi.ID ASC;