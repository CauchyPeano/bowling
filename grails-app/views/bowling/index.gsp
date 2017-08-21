<!doctype html>
<html lang="en" class="no-js">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>

    </title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>

    %{--<asset:stylesheet src="application.css"/>--}%

    %{--<g:layoutHead/>--}%
</head>

<body>

<h1>This is bowling game</h1>

%{--<g:layoutBody>--}%
<g:form controller="bowling">
    <g:field type="number" name="pins_amount"/>
    <g:actionSubmit name="btnSubmit" value="Roll!" action="roll"/>
</g:form>

<g:renderErrors bean="${game}" />

${game.toString()}



%{--</g:layoutBody>--}%
</body>
</html>
