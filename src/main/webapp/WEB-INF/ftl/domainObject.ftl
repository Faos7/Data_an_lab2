<#-- @ftlvariable name="domainObject" type="com.faos7.entity.DomainObject" -->
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

<h1>charasteristic Estimations</h1>

<table>
    <thead>
    <tr>
        <th>имя</th>
        <th>отклонение</th>
        <th>диапазон</th>
        <th>значение</th>
    </tr>
    </thead>
    <tbody>
    <#list domainObject.characteristicEstimations as estimations>
    <tr>
        <td>${estimations.name}</td>
        <td>${estimations.deviation}</td>
        <td>${estimations.range}</td>
        <td>${estimations.value}</td>
    </tr>
    </#list>
    </tbody>
</table>
</body>
</html>