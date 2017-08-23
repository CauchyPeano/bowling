<!doctype html>
<html lang="en" class="no-js">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>

    </title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>

    <style>
    table {
        border-collapse: collapse;
    }

    table, th {
        border: 1px solid black;
        padding: 10px;
    }

    td {
        padding: 10px;
    }

    td.box {
        border: 1px solid black;
    }
    td.score {
        text-align: center;
        border-right: 1px solid black;
    }
    </style>
    %{--<asset:stylesheet src="application.css"/>--}%

    %{--<g:layoutHead/>--}%
</head>

<body>

<h1>This is bowling game</h1>

%{--
<g:layoutBody>
--}%
<g:form controller="bowling">
    <g:field type="number" name="pins_amount"/>
    <g:actionSubmit name="btnSubmit" value="Roll!" action="roll"/>
</g:form>

%{--<g:renderErrors bean="${game}"/>--}%

<br/>

<table>
    <tbody>
    <tr>
        <g:each var="i" in="${(0..8)}">
            <th colspan="2">${i + 1}</th>
        </g:each>
        <th colspan="3">10</th>
    </tr>
    <tr>
        <g:each var="i" in="${(0..8)}">

            <td>1</td>
            <td class="box">2</td>

        </g:each>

        <td>1</td>
        <td class="box">2</td>
        <td class="box">2</td>
    </tr>

    <tr>
        <g:each var="i" in="${(0..8)}">
            <td class="score" colspan="2">3</td>
        </g:each>
        <td class="score" colspan="3">3</td>
    </tr>
    </tbody>
</table>


%{--<tbody style="border: 1px solid black">--}%
%{--<tr>--}%
    %{--<g:each var="i" in="${(0..9)}">--}%

        %{--<td></td>--}%
        %{--<td></td>--}%

    %{--</g:each>--}%
%{--</tr>--}%
%{--<tr>--}%
    %{--<g:each var="i" in="${(0..9)}">--}%

        %{--<td></td>--}%
        %{--<td></td>--}%

    %{--</g:each>--}%
%{--</tr>--}%
%{--</tbody>--}%

%{--${game.toString()}--}%

%{--</g:layoutBody>--}%
</body>
</html>
