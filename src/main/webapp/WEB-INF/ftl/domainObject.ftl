<#-- @ftlvariable name="domainObject" type="com.data_an_lab2.entity.DomainObject" -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Calculations</title>
</head>
<body>
<nav role="navigation">
    <ul>
        <li><a href="/">Home</a></li>
    </ul>
</nav>

<h1>Вариационный ряд</h1>
<table>
    <thead>
    <tr>
        <th>№</th>
        <th>Значение варианты</th>
        <th>Частота</th>
        <th>Относительная частота</th>
        <th>Значения емпирической функции распределения</th>
    </tr>
    </thead>
    <tbody>
    <#list domainObject.varOuts as varout>
    <tr>
        <td>${varout.num}</td>
        <td>${varout.data}</td>
        <td>${varout.frequency}</td>
        <td>${varout.relFreq}</td>
        <td>${varout.emp}</td>
    </tr>
    </#list>
    </tbody>
</table>

<h1>Классы вариационного ряда</h1>
<table>
    <thead>
    <tr>
        <th>Границы класса</th>
        <th>Частота</th>
        <th>Относительная частота</th>
        <th>Значения емпирической функции распределения</th>
    </tr>
    </thead>
    <tbody>
    <#list domainObject.classesOuts as classesOut>
    <tr>
        <td>${classesOut.limits}</td>
        <td>${classesOut.frequency}</td>
        <td>${classesOut.relFreq}</td>
        <td>${classesOut.emp}</td>
    </tr>
    </#list>
    </tbody>
</table>

<h1>Характеристики выборки</h1>

<table>
    <thead>
    <tr>
        <th>имя</th>
        <th>значение</th>
        <th>отклонение</th>
        <th>диапазон</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <th>среднее арифметическое</th>
        <th>${domainObject.avarageVal}</th>
        <th>${domainObject.rMsAvarageVal}</th>
        <th>${domainObject.avarageIntrval}</th>
    </tr>
    <tr>
        <th>медиана</th>
        <th>${domainObject.medianVal}</th>
        <th>${domainObject.rMsMedian}</th>
        <th>${domainObject.medianInterval}</th>
    </tr>
    <tr>
        <th>Среднеквадратическое</th>
        <th>${domainObject.rMsVal}</th>
        <th>${domainObject.rMsRMSVal}</th>
        <th>${domainObject.rMsInterval}</th>
    </tr>
    <tr>
        <th>Коэффициент ассиметрии</th>
        <th>${domainObject.coefOfAsymethriaVal}</th>
        <th>${domainObject.rMsCoefOfAsymethriaVal}</th>
        <th>${domainObject.coefOfAsymethrioaInterval}</th>
    </tr>
    <tr>
        <th>Коэффициент эксцесса</th>
        <th>${domainObject.coefExcessVal}</th>
        <th>${domainObject.rMsCoefExcessVal}</th>
        <th>${domainObject.coefExcessInterval}</th>
    </tr>
    <tr>
        <th>Коэффициент контрексцесса</th>
        <th>${domainObject.coefContrExcessVal}</th>
        <th>${domainObject.rMsCoefContrExcessVal}</th>
        <th>${domainObject.coefContrExcessInterval}</th>
    </tr><tr>
        <th>Коэффициент вариации</th>
        <th>${domainObject.pearsonCoefVariationVal}</th>
        <th>${domainObject.rMsPearsonCoefVariationVal}</th>
        <th>${domainObject.pearsonInterval}</th>
    </tr>

    </tbody>
</table>

<h1>Параметры нормального распределения</h1>

<table>
    <thead>
    <tr>
        <th>Параметр</th>
        <th>Значение оценки</th>
        <th>Среднеквадратическое отклонение</th>
        <th>Доверительный интервал </th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <th>~a</th>
        <th>${domainObject.aEvaulation}</th>
        <th>${domainObject.rMsAEvaulation}</th>
        <th>${domainObject.aInterval}</th>
    </tr>
    <tr>
        <th>~b</th>
        <th>${domainObject.bEvaulation}</th>
        <th>${domainObject.rMsBEvaulation}</th>
        <th>${domainObject.bInterval}</th>
    </tr>
    <tr>
        <th>~(a+b)/2</th>
        <th>${domainObject.m1Evaulation}</th>
        <th>${domainObject.rMsM1Evaulation}</th>
        <th>${domainObject.m1Interval}</th>
    </tr>
    </tbody>
</table>

<h1>Критерий согласия Колмогорова</h1>
<table>
    <thead>
    <tr>
        <th>kz</th>
        <th>р</th>
        <th>альфа</th>
        <th>Вывод</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <th>${domainObject.kz}</th>
        <th>${domainObject.p}</th>
        <th>${domainObject.alpha}</th>
        <th>${domainObject.isReliable}</th>
    </tr>


    </tbody>
</table>

</body>
</html>