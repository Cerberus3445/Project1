<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <style type="text/css">
        *, *::before, *::after { box-sizing:border-box }
        body {
            margin:0;
            background:Black;
        }

        .shape {
            margin:0 auto;
            aspect-ratio: 1;
            position:relative;

            --width: 100%;
            --scale: 1;
            --opacity: 0.66;
            --top: 0;
            --left:0;
            --path: polygon(0% 0%, 100% 0%, 100% 100%, 0% 100%);
            --background: linear-gradient( hotpink, red, orange, yellow, hotpink);

            --offset: 0deg;
            --speed: 8000ms;

            clip-path: var(--path);
            background: var(--background);
            scale: var(--scale);
            opacity: var(--opacity);
            width:var(--width);
            top:var(--top);
            left:var(--left);
            rotate: var(--offset);

            mix-blend-mode: difference;

            animation: turn var(--speed) linear forwards infinite;

            @keyframes turn {
                to {
                    rotate: calc( var(--offset) + 1turn);
                }
            }
        }

        .blur-container {

            --blur: 40px;
            filter: blur( var(--blur) );
            height:100vh;
            width:100%;
            display: grid;
            > * {
                grid-column: 1 / -1;
                grid-row: 1 / -1;
            }
            overflow:hidden;
        }
        .top{
            display: flex;
            justify-content: center;
            align-items: center;
            height: 10vh;
        }
    </style>
</head>
<body>
<div class="top">
    <h1 style="color: white">Welcome to Library Page</h1>
</div>
<hr>
<a style="color:white;" href="/people">People page</a>
<a style="color: white" href="/books">Books page</a>
<div class="blur-container"
     style="--blur:12vw;">

    <div class="shape"
         style="--path:polygon(50.9% 37.2%, 43.5% 34.7%, 33.6% 26.1%, 39.2% 10.8%, 26.2% 0.0%, 4.8% 6.4%, 0.0% 30.4%, 20.7% 37.2%, 33.4% 26.3%, 43.2% 34.9%, 45.0% 35.6%, 43.6% 46.4%, 37.8% 59.5%, 21.8% 63.2%, 11.7% 76.1%, 22.9% 91.3%, 47.4% 91.3%, 54.0% 79.0%, 38.0% 59.6%, 43.9% 46.4%, 45.2% 35.5%, 50.9% 37.6%, 56.1% 36.8%, 59.8% 47.6%, 70.3% 61.9%, 87.7% 56.0%, 96.4% 37.4%, 88.6% 15.1%, 63.7% 16.7%, 55.2% 33.6%, 55.9% 36.6%, 50.9% 37.2%);"></div>

    <div class="shape"
         style="--path:polygon(50.9% 37.2%, 43.5% 34.7%, 33.6% 26.1%, 39.2% 10.8%, 26.2% 0.0%, 4.8% 6.4%, 0.0% 30.4%, 20.7% 37.2%, 33.4% 26.3%, 43.2% 34.9%, 45.0% 35.6%, 43.6% 46.4%, 37.8% 59.5%, 21.8% 63.2%, 11.7% 76.1%, 22.9% 91.3%, 47.4% 91.3%, 54.0% 79.0%, 38.0% 59.6%, 43.9% 46.4%, 45.2% 35.5%, 50.9% 37.6%, 56.1% 36.8%, 59.8% 47.6%, 70.3% 61.9%, 87.7% 56.0%, 96.4% 37.4%, 88.6% 15.1%, 63.7% 16.7%, 55.2% 33.6%, 55.9% 36.6%, 50.9% 37.2%);
       --offset:180deg;
       --speed: 6000ms;
       --background: linear-gradient( cyan, blue, green, purple, cyan);
       "></div>
</div>
</body>
</html>