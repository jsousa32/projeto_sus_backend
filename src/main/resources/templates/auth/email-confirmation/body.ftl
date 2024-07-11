<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>IntegraNF-e</title>
    <style>
        html,
        body,
        div,
        span,
        applet,
        object,
        iframe,
        h1,
        h2,
        h3,
        h4,
        h5,
        h6,
        p,
        blockquote,
        pre,
        a,
        abbr,
        acronym,
        address,
        big,
        cite,
        code,
        del,
        dfn,
        em,
        img,
        ins,
        kbd,
        q,
        s,
        samp,
        small,
        strike,
        strong,
        sub,
        sup,
        tt,
        var,
        b,
        u,
        i,
        center,
        dl,
        dt,
        dd,
        ol,
        ul,
        li,
        fieldset,
        form,
        label,
        legend,
        table,
        caption,
        tbody,
        tfoot,
        thead,
        tr,
        th,
        td,
        article,
        aside,
        canvas,
        details,
        embed,
        figure,
        figcaption,
        footer,
        header,
        hgroup,
        menu,
        nav,
        output,
        ruby,
        section,
        summary,
        time,
        mark,
        audio,
        video {
            margin: 0;
            padding: 0;
            border: 0;
            font: inherit;
            vertical-align: baseline;
        }

        article,
        aside,
        details,
        figcaption,
        figure,
        footer,
        header,
        hgroup,
        menu,
        nav,
        section {
            display: block;
        }

        body {
            line-height: 1;
        }

        ol,
        ul {
            list-style: none;
        }

        blockquote,
        q {
            quotes: none;
        }

        blockquote:before,
        blockquote:after,
        q:before,
        q:after {
            content: none;
        }

        table {
            border-collapse: collapse;
            border-spacing: 0;
        }

        html {
            font-family: system-ui, -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen, Ubuntu, Cantarell,
            "Open Sans", "Helvetica Neue", sans-serif;
        }

        body,
        html {
            height: 100%;
        }

        span {
            font-weight: 600;
        }

        button {
            background-color: #2857ab;
            border: none;
            border-radius: 4px;
            color: white;
            font-size: 1.2em;
            padding: 0.375em 1em;
            cursor: pointer;
        }

        .table {
            background-color: #2857ab;
            height: 100%;
            width: 100%;
        }

        .body {
            background-color: white;
            color: #000000;
            padding: 2em;
        }

        .body-head {
            text-align: center;
            font-size: 1.5em;
            padding: 1.5em 1.5em 1em 1.5em;
            font-weight: 600;
            border-radius: 5px 5px 0 0;
        }

        .body-side {
            background-color: #f0f0f0;
        }

        .body-content {
            text-align: center;
        }

        .name {
            text-align: justify;
            padding-bottom: 30px;
        }

        .body-content> :first-child {
            padding-bottom: 2em;
        }

        .body-content>p,
        .body-content>div {
            padding-bottom: 1em;
        }

        .body-content> :last-child {
            padding-bottom: 0;
        }

        .text {
            color: #4d4d4d;
            font-weight: 500;
            font-size: 17px;
        }

        .text2 {
            color: #4d4d4d;
            font-weight: 500;
            font-size: 15px;
            padding-top: 10px;
        }

        .atenciosamente {
            color: #4d4d4d;
            font-weight: 500;
            font-size: 15px;
            margin-top: 5em;
            text-align: justify;
        }

        .text3 {
            color: #646d71;
            font-weight: 500;
            font-size: 15px;
            margin-top: 10px;
            text-align: justify;
        }

        .body-foot {
            color: #2857ab;
            font-size: 0.9em;
            text-align: justify;
            border-radius: 0 0 5px 5px;
        }

        .body-foot> :first-child {
            padding-bottom: 0.5em;
        }

        .footer {
            height: 100%;
            text-align: center;
            padding: 2.5em 0;
            color: #ffffff;
            font-weight: 500;
            font-size: 18px;
        }

        .title {
            max-height: 4em;
            padding: 1.2em 0;
            width: 80%;
            text-align: center;
            font-size: 2.8em;
            font-weight: 600;
            color: white;
        }

        .token {
            word-break: break-all;
            color: white;
            background-color: #2857ab;
            border-radius: 10px;
            font-size: 0.8em;
            padding: 1.2em !important;
            font-weight: 500;
            margin-bottom: 1rem;
        }

        .text-decoration {
            color: #000000;
            font-weight: 700;
            font-size: 17px;
        }

        .content-center {
            text-align: center;
        }

        .content-center>.token {
            display: inline-block;
            font-size: 1.2em;
            line-height: 0;
            padding: 1em;
        }
    </style>
</head>

<body>
<table class="table gradient">
    <thead>
    <tr>
        <th></th>
        <th class="title">
            Fake SUS
        </th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td></td>
        <td class="body body-head">
            <p class="name">Olá <span>${name}</span>,</p>
            <p>Confirme seu email</p>
        </td>
        <td></td>
    </tr>
    <tr>
        <td class="body-side"></td>
        <td class="body">
            <div class="body-content">
                <p class="text">O seu <strong class="text-decoration">token</strong> para confirmação de email
                    é:</p>
                <div class="content-center">
                    <p class="token">${token}</p>
                </div>
                <p class="text">
                    Lembre-se: por segurança,
                    <span class="text-decoration">não compartilhe</span> este token com outras pessoas!
                </p>
                <div>
                    <p class="text body">Surgindo alguma dúvida ou necessitando de ajuda,</p>
                    <p class="text">
                        entre em contato conosco através do e-mail
                        <strong class="text-decoration">contato@fakesus.com.br</strong>.
                    </p>
                </div>
            </div>

            <div class="body-foot">
                <p class="atenciosamente">Antenciosamente,</p>
                <p class="text2 text-decoration">Equipe FakeSUS</p>
            </div>
        </td>

        <td class="body-side"></td>
    </tr>
    <tr>
        <td></td>
        <td class="body body-foot">
            <p class="text3">Enviado por <span>FakeSUS</span>.</p>
        </td>
        <td></td>
    </tr>
    </tbody>
    <tfoot style="height: 100%">
    <tr>
        <td></td>
        <td class="footer">
            <p>Esta é uma mensagem automática.</p>
        </td>
        <td></td>
    </tr>
    </tfoot>
</table>
</body>

</html>