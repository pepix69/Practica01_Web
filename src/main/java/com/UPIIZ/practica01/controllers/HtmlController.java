package com.UPIIZ.practica01.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;

@Controller
public class HtmlController {

    private static final LinkedHashMap<String, EtiquetaInfo> ETIQUETAS = new LinkedHashMap<>();
    private static final LinkedHashMap<String, List<String>> CATEGORIAS = new LinkedHashMap<>();

    static {
        // ── ESTRUCTURA ──────────────────────────────────────────────
        agregar("html", "Estructura",
                "Define el documento HTML completo. Es el elemento raíz que envuelve todo el contenido de la página web.",
                "<html lang=\"es\">\n  ...\n</html>",
                "lang: idioma del documento (ej. 'es', 'en')",
                "<html lang=\"es\">\n  <head>\n    <title>Mi página</title>\n  </head>\n  <body>\n    <p>Hola mundo</p>\n  </body>\n</html>",
                "html");

        agregar("head", "Estructura",
                "Contiene metadatos del documento: título, charset, enlaces a CSS/JS, etc. Su contenido NO se muestra visualmente.",
                "<head>\n  ...\n</head>",
                "No tiene atributos propios destacados",
                "<head>\n  <meta charset=\"UTF-8\">\n  <title>Mi sitio</title>\n  <link rel=\"stylesheet\" href=\"style.css\">\n</head>",
                "head");

        agregar("body", "Estructura",
                "Contiene todo el contenido visible de la página: texto, imágenes, enlaces, tablas, formularios, etc.",
                "<body>\n  ...\n</body>",
                "onload, onunload (manejadores de eventos globales)",
                "<body>\n  <h1>Bienvenido</h1>\n  <p>Contenido de la página</p>\n</body>",
                "body");

        agregar("meta", "Estructura",
                "Proporciona metadatos sobre el documento HTML. Usada para charset, descripción, viewport, SEO y más.",
                "<meta name=\"...\" content=\"...\">",
                "charset, name, content, http-equiv, viewport",
                "<meta charset=\"UTF-8\">\n<meta name=\"description\" content=\"Mi sitio web\">\n<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">",
                "meta");

        agregar("title", "Estructura",
                "Define el título del documento que aparece en la pestaña del navegador y en los resultados de búsqueda.",
                "<title>Texto del título</title>",
                "No tiene atributos específicos",
                "<head>\n  <title>Catálogo HTML - Inicio</title>\n</head>",
                "title");

        // ── TEXTO ────────────────────────────────────────────────────
        agregar("h1", "Texto",
                "Encabezado de nivel 1. Es el título principal de la página. Debe usarse solo una vez por página para SEO.",
                "<h1>Título principal</h1>",
                "class, id, style (atributos globales)",
                "<h1>Catálogo de Etiquetas HTML</h1>",
                "h1");

        agregar("h2", "Texto",
                "Encabezado de nivel 2. Define secciones principales dentro del contenido.",
                "<h2>Subtítulo</h2>",
                "class, id, style (atributos globales)",
                "<h2>Etiquetas de Texto</h2>\n<p>En esta sección veremos...</p>",
                "h2");

        agregar("h3", "Texto",
                "Encabezado de nivel 3. Define subsecciones dentro de las secciones h2.",
                "<h3>Subtítulo nivel 3</h3>",
                "class, id, style (atributos globales)",
                "<h3>Descripción de la etiqueta</h3>",
                "h3");

        agregar("p", "Texto",
                "Define un párrafo de texto. El navegador agrega espacio antes y después de cada párrafo automáticamente.",
                "<p>Contenido del párrafo</p>",
                "class, id, style, align (obsoleto)",
                "<p>Este es el primer párrafo de mi artículo.</p>\n<p>Y este es el segundo párrafo.</p>",
                "p");

        agregar("span", "Texto",
                "Contenedor en línea genérico. No añade estilo visual por sí mismo, pero permite aplicar estilos a partes de texto.",
                "<span>Texto</span>",
                "class, id, style (atributos globales)",
                "<p>El cielo es <span style=\"color:blue\">azul</span> hoy.</p>",
                "span");

        agregar("strong", "Texto",
                "Indica importancia semántica alta. El texto se muestra en negrita. Los lectores de pantalla le dan énfasis.",
                "<strong>Texto importante</strong>",
                "class, id, style (atributos globales)",
                "<p><strong>Advertencia:</strong> No olvides guardar tu trabajo.</p>",
                "strong");

        agregar("em", "Texto",
                "Indica énfasis semántico. El texto se muestra en cursiva. Los lectores de pantalla cambian el tono de voz.",
                "<em>Texto con énfasis</em>",
                "class, id, style (atributos globales)",
                "<p>Debes <em>siempre</em> revisar tu código antes de publicar.</p>",
                "em");

        agregar("br", "Texto",
                "Inserta un salto de línea dentro del texto. Es una etiqueta vacía (no tiene cierre).",
                "<br>",
                "No tiene atributos relevantes",
                "<p>Línea uno<br>Línea dos<br>Línea tres</p>",
                "br");

        agregar("hr", "Texto",
                "Inserta una línea horizontal temática que separa contenido. Tiene significado semántico de cambio de tema.",
                "<hr>",
                "class, id, style",
                "<h2>Sección 1</h2>\n<p>Contenido...</p>\n<hr>\n<h2>Sección 2</h2>",
                "hr");

        agregar("blockquote", "Texto",
                "Define una sección citada de otra fuente. El navegador la muestra con margen izquierdo.",
                "<blockquote cite=\"url\">Cita...</blockquote>",
                "cite: URL de la fuente original",
                "<blockquote cite=\"https://example.com\">\n  <p>El código es poesía.</p>\n</blockquote>",
                "blockquote");

        agregar("code", "Texto",
                "Define código de computadora en línea. El texto se muestra en fuente monoespaciada.",
                "<code>código aquí</code>",
                "class, id, style (atributos globales)",
                "<p>Usa la función <code>console.log()</code> para depurar.</p>",
                "code");

        agregar("pre", "Texto",
                "Muestra texto preformateado conservando espacios, tabulaciones y saltos de línea exactos.",
                "<pre>texto preformateado</pre>",
                "class, id, style (atributos globales)",
                "<pre>\nfunction hola() {\n  console.log('Hola mundo');\n}\n</pre>",
                "pre");

        // ── ENLACES E IMÁGENES ───────────────────────────────────────
        agregar("a", "Enlaces e Imágenes",
                "Define un hipervínculo. Puede enlazar a otras páginas, secciones, emails o archivos.",
                "<a href=\"url\">Texto del enlace</a>",
                "href: destino, target: _blank/_self, rel: nofollow/noopener, download",
                "<a href=\"https://google.com\" target=\"_blank\" rel=\"noopener\">Visita Google</a>",
                "a");

        agregar("img", "Enlaces e Imágenes",
                "Inserta una imagen en la página. Es una etiqueta vacía (no necesita cierre).",
                "<img src=\"imagen.jpg\" alt=\"descripción\">",
                "src: ruta, alt: texto alternativo, width, height, loading: lazy",
                "<img src=\"https://via.placeholder.com/200x100\" alt=\"Imagen de ejemplo\" width=\"200\">",
                "img");

        // ── LISTAS ───────────────────────────────────────────────────
        agregar("ul", "Listas",
                "Define una lista desordenada (con viñetas). Los elementos no siguen un orden específico.",
                "<ul>\n  <li>elemento</li>\n</ul>",
                "class, id, style, type (obsoleto)",
                "<ul>\n  <li>Manzanas</li>\n  <li>Naranjas</li>\n  <li>Plátanos</li>\n</ul>",
                "ul");

        agregar("ol", "Listas",
                "Define una lista ordenada (numerada). Los elementos tienen un orden secuencial significativo.",
                "<ol>\n  <li>elemento</li>\n</ol>",
                "type: 1/a/A/i/I, start: número inicial, reversed",
                "<ol>\n  <li>Mezcla los ingredientes</li>\n  <li>Hornea 30 minutos</li>\n  <li>Deja enfriar</li>\n</ol>",
                "ol");

        agregar("li", "Listas",
                "Define un elemento de lista. Debe ser hijo directo de ul, ol o menu.",
                "<li>Elemento</li>",
                "value: (solo en ol) número de este ítem",
                "<ul>\n  <li>Primer ítem</li>\n  <li>Segundo ítem</li>\n</ul>",
                "li");

        agregar("dl", "Listas",
                "Define una lista de descripción (pares término-definición). Útil para glosarios o metadatos.",
                "<dl>\n  <dt>Término</dt>\n  <dd>Definición</dd>\n</dl>",
                "class, id, style (atributos globales)",
                "<dl>\n  <dt>HTML</dt>\n  <dd>Lenguaje de marcado para páginas web</dd>\n  <dt>CSS</dt>\n  <dd>Lenguaje de estilos visuales</dd>\n</dl>",
                "dl");

        // ── TABLAS ───────────────────────────────────────────────────
        agregar("table", "Tablas",
                "Define una tabla HTML para mostrar datos en filas y columnas. No debe usarse para maquetación.",
                "<table>\n  <tr><td>...</td></tr>\n</table>",
                "border (obsoleto), class, id, style",
                "<table border=\"1\">\n  <tr><th>Nombre</th><th>Edad</th></tr>\n  <tr><td>Ana</td><td>25</td></tr>\n</table>",
                "table");

        agregar("tr", "Tablas",
                "Define una fila de tabla (table row). Contiene celdas td o th.",
                "<tr>\n  <td>celda</td>\n</tr>",
                "class, id, style, align (obsoleto)",
                "<table>\n  <tr>\n    <td>Celda 1</td>\n    <td>Celda 2</td>\n  </tr>\n</table>",
                "tr");

        agregar("td", "Tablas",
                "Define una celda de datos en una tabla. Contiene el dato de esa posición (fila × columna).",
                "<td>contenido</td>",
                "colspan, rowspan, headers, class, style",
                "<tr>\n  <td colspan=\"2\">Celda que ocupa 2 columnas</td>\n</tr>",
                "td");

        agregar("th", "Tablas",
                "Define una celda de encabezado. Su texto aparece en negrita y centrado por defecto. Mejora la accesibilidad.",
                "<th>Encabezado</th>",
                "scope: col/row/colgroup/rowgroup, colspan, rowspan",
                "<tr>\n  <th scope=\"col\">Producto</th>\n  <th scope=\"col\">Precio</th>\n</tr>",
                "th");

        agregar("thead", "Tablas",
                "Agrupa el encabezado de una tabla. Permite estilos diferenciados y mejora la accesibilidad.",
                "<thead>\n  <tr><th>...</th></tr>\n</thead>",
                "class, id, style",
                "<table>\n  <thead>\n    <tr><th>Col1</th><th>Col2</th></tr>\n  </thead>\n  <tbody>\n    <tr><td>A</td><td>B</td></tr>\n  </tbody>\n</table>",
                "thead");

        // ── FORMULARIOS ──────────────────────────────────────────────
        agregar("form", "Formularios",
                "Define un formulario HTML para recopilar datos del usuario que serán enviados al servidor.",
                "<form action=\"/ruta\" method=\"post\">\n  ...\n</form>",
                "action: URL destino, method: get/post, enctype, novalidate, autocomplete",
                "<form action=\"/buscar\" method=\"get\">\n  <input type=\"text\" name=\"q\" placeholder=\"Buscar...\">\n  <button type=\"submit\">Buscar</button>\n</form>",
                "form");

        agregar("input", "Formularios",
                "Campo de entrada de datos. Es muy versátil según su atributo type: texto, contraseña, email, número, etc.",
                "<input type=\"text\" name=\"campo\">",
                "type, name, value, placeholder, required, disabled, readonly, min, max, pattern",
                "<input type=\"text\" placeholder=\"Tu nombre\" required>\n<input type=\"email\" placeholder=\"tu@email.com\">\n<input type=\"number\" min=\"1\" max=\"100\">",
                "input");

        agregar("label", "Formularios",
                "Define una etiqueta para un elemento de formulario. Mejora la usabilidad y accesibilidad.",
                "<label for=\"id-campo\">Texto</label>",
                "for: id del campo asociado, form",
                "<label for=\"nombre\">Nombre completo:</label>\n<input type=\"text\" id=\"nombre\" name=\"nombre\">",
                "label");

        agregar("button", "Formularios",
                "Define un botón clickeable. Más flexible que input type button, puede contener HTML.",
                "<button type=\"button\">Texto</button>",
                "type: submit/reset/button, disabled, form, name, value",
                "<button type=\"submit\">Enviar formulario</button>\n<button type=\"reset\">Limpiar</button>\n<button type=\"button\" onclick=\"alert('Hola')\">Clic aquí</button>",
                "button");

        agregar("select", "Formularios",
                "Define una lista desplegable de opciones para que el usuario elija una o varias.",
                "<select name=\"opcion\">\n  <option value=\"v\">Texto</option>\n</select>",
                "name, multiple, size, required, disabled, form",
                "<select name=\"pais\">\n  <option value=\"mx\">México</option>\n  <option value=\"es\">España</option>\n  <option value=\"ar\">Argentina</option>\n</select>",
                "select");

        agregar("textarea", "Formularios",
                "Define un campo de texto multilínea donde el usuario puede escribir texto largo.",
                "<textarea name=\"mensaje\" rows=\"4\" cols=\"50\"></textarea>",
                "name, rows, cols, placeholder, required, disabled, readonly, maxlength",
                "<textarea name=\"comentario\" rows=\"5\" cols=\"40\"\n  placeholder=\"Escribe tu comentario aquí...\"></textarea>",
                "textarea");

        agregar("option", "Formularios",
                "Define una opción dentro de un select o datalist. Cada option representa una elección posible.",
                "<option value=\"valor\">Texto visible</option>",
                "value, selected, disabled, label",
                "<select>\n  <option value=\"\">-- Selecciona --</option>\n  <option value=\"a\" selected>Opción A</option>\n  <option value=\"b\" disabled>Opción B (desactivada)</option>\n</select>",
                "option");

        // ── MULTIMEDIA ───────────────────────────────────────────────
        agregar("audio", "Multimedia",
                "Inserta contenido de audio. Permite controles nativos del navegador y múltiples formatos.",
                "<audio controls>\n  <source src=\"audio.mp3\" type=\"audio/mpeg\">\n</audio>",
                "src, controls, autoplay, loop, muted, preload: auto/metadata/none",
                "<audio controls>\n  <source src=\"musica.mp3\" type=\"audio/mpeg\">\n  <source src=\"musica.ogg\" type=\"audio/ogg\">\n  Tu navegador no soporta audio HTML5.\n</audio>",
                "audio");

        agregar("video", "Multimedia",
                "Inserta contenido de video con controles nativos del navegador. Soporta múltiples formatos.",
                "<video width=\"320\" height=\"240\" controls>\n  <source src=\"video.mp4\" type=\"video/mp4\">\n</video>",
                "src, width, height, controls, autoplay, loop, muted, poster, preload",
                "<video width=\"400\" controls poster=\"thumb.jpg\">\n  <source src=\"pelicula.mp4\" type=\"video/mp4\">\n  Tu navegador no soporta video HTML5.\n</video>",
                "video");

        agregar("source", "Multimedia",
                "Especifica una fuente de media para audio, video o picture. Permite múltiples formatos para compatibilidad.",
                "<source src=\"archivo.mp4\" type=\"video/mp4\">",
                "src: URL, type: tipo MIME, media: condición CSS",
                "<video controls>\n  <source src=\"video.mp4\" type=\"video/mp4\">\n  <source src=\"video.webm\" type=\"video/webm\">\n</video>",
                "source");

        agregar("iframe", "Multimedia",
                "Inserta un documento HTML externo dentro de la página actual (frame en línea).",
                "<iframe src=\"url\" width=\"500\" height=\"300\"></iframe>",
                "src, width, height, title, sandbox, allow, loading: lazy",
                "<iframe width=\"560\" height=\"315\"\n  src=\"https://www.youtube.com/embed/dQw4w9WgXcQ\"\n  title=\"Video YouTube\"\n  frameborder=\"0\"\n  allowfullscreen></iframe>",
                "iframe");

        // ── SEMÁNTICAS ───────────────────────────────────────────────
        agregar("header", "Semánticas",
                "Define la cabecera de una página o sección. Típicamente contiene logo, navegación y título principal.",
                "<header>\n  ...\n</header>",
                "class, id, style (atributos globales)",
                "<header>\n  <h1>Mi Sitio Web</h1>\n  <nav>\n    <a href=\"/\">Inicio</a>\n    <a href=\"/acerca\">Acerca</a>\n  </nav>\n</header>",
                "header");

        agregar("footer", "Semánticas",
                "Define el pie de página de un documento o sección. Contiene info de contacto, copyright, enlaces legales.",
                "<footer>\n  ...\n</footer>",
                "class, id, style (atributos globales)",
                "<footer>\n  <p>&copy; 2024 Mi Empresa. Todos los derechos reservados.</p>\n  <a href=\"/privacidad\">Política de Privacidad</a>\n</footer>",
                "footer");

        agregar("nav", "Semánticas",
                "Define un bloque de navegación principal. Los lectores de pantalla lo identifican como área de navegación.",
                "<nav>\n  <a href=\"/\">Inicio</a>\n</nav>",
                "class, id, style, aria-label",
                "<nav aria-label=\"Navegación principal\">\n  <ul>\n    <li><a href=\"/\">Inicio</a></li>\n    <li><a href=\"/blog\">Blog</a></li>\n    <li><a href=\"/contacto\">Contacto</a></li>\n  </ul>\n</nav>",
                "nav");

        agregar("main", "Semánticas",
                "Define el contenido principal único del documento. Solo debe existir uno por página.",
                "<main>\n  ...\n</main>",
                "class, id, style (atributos globales)",
                "<body>\n  <header>...</header>\n  <main>\n    <h1>Contenido principal</h1>\n    <p>Artículo principal aquí</p>\n  </main>\n  <footer>...</footer>\n</body>",
                "main");

        agregar("section", "Semánticas",
                "Define una sección temática del documento. Cada sección idealmente tiene su propio encabezado.",
                "<section>\n  <h2>Título de sección</h2>\n  ...\n</section>",
                "class, id, style, aria-labelledby",
                "<section id=\"servicios\">\n  <h2>Nuestros Servicios</h2>\n  <p>Ofrecemos diseño web, desarrollo...</p>\n</section>",
                "section");

        agregar("article", "Semánticas",
                "Define contenido independiente y autónomo: artículo de blog, noticia, comentario. Puede ser distribuido por separado.",
                "<article>\n  <h2>Título</h2>\n  <p>Contenido...</p>\n</article>",
                "class, id, style (atributos globales)",
                "<article>\n  <h2>Cómo aprender HTML</h2>\n  <p>Publicado el 1 de enero de 2024</p>\n  <p>El HTML es el lenguaje base de la web...</p>\n</article>",
                "article");

        agregar("aside", "Semánticas",
                "Define contenido relacionado pero secundario (barra lateral, notas, publicidad, enlaces relacionados).",
                "<aside>\n  ...\n</aside>",
                "class, id, style (atributos globales)",
                "<article>\n  <p>Artículo principal...</p>\n</article>\n<aside>\n  <h3>Artículos relacionados</h3>\n  <ul><li><a href=\"#\">Otro artículo</a></li></ul>\n</aside>",
                "aside");

        agregar("div", "Semánticas",
                "Contenedor genérico de bloque sin significado semántico. Se usa para agrupar elementos y aplicar estilos.",
                "<div class=\"contenedor\">\n  ...\n</div>",
                "class, id, style (atributos globales)",
                "<div class=\"tarjeta\" style=\"border:1px solid #ccc; padding:16px\">\n  <h3>Título de tarjeta</h3>\n  <p>Descripción de la tarjeta</p>\n</div>",
                "div");

        agregar("details", "Semánticas",
                "Crea un widget de apertura/cierre interactivo. El contenido está oculto por defecto y se revela al hacer clic.",
                "<details>\n  <summary>Título</summary>\n  Contenido oculto\n</details>",
                "open: (booleano) si está abierto por defecto",
                "<details>\n  <summary>Ver más información</summary>\n  <p>Este contenido se muestra al hacer clic.</p>\n</details>",
                "details");

        agregar("summary", "Semánticas",
                "Define el título visible de un elemento details. Es el texto clickeable que muestra u oculta el contenido.",
                "<summary>Texto del resumen</summary>",
                "class, id, style (atributos globales)",
                "<details>\n  <summary>¿Qué es HTML?</summary>\n  <p>HTML es el Lenguaje de Marcado de Hipertexto.</p>\n</details>",
                "summary");

        // ── CONSTRUIR MAPA DE CATEGORÍAS ─────────────────────────────
        for (EtiquetaInfo e : ETIQUETAS.values()) {
            CATEGORIAS.computeIfAbsent(e.getCategoria(), k -> new ArrayList<>()).add(e.getNombre());
        }
    }

    private static void agregar(String nombre, String categoria, String descripcion,
                                String sintaxis, String atributos,
                                String ejemploCodigo, String ejemploRenderizado) {
        ETIQUETAS.put(nombre, new EtiquetaInfo(nombre, categoria, descripcion,
                sintaxis, atributos, ejemploCodigo, ejemploRenderizado));
    }

    // ── RUTAS ─────────────────────────────────────────────────────────

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("categorias", CATEGORIAS);
        model.addAttribute("etiquetas", ETIQUETAS);
        model.addAttribute("totalEtiquetas", ETIQUETAS.size());
        return "index";
    }

    @GetMapping("/etiqueta/{nombre}")
    public String etiqueta(@PathVariable String nombre, Model model) {
        EtiquetaInfo info = ETIQUETAS.get(nombre);
        if (info == null) return "redirect:/";

        List<String> claves = new ArrayList<>(ETIQUETAS.keySet());
        int idx = claves.indexOf(nombre);

        model.addAttribute("etiqueta", info);
        model.addAttribute("anterior", idx > 0 ? claves.get(idx - 1) : null);
        model.addAttribute("siguiente", idx < claves.size() - 1 ? claves.get(idx + 1) : null);
        model.addAttribute("posicion", idx + 1);
        model.addAttribute("total", claves.size());
        model.addAttribute("categorias", CATEGORIAS);

        return "etiqueta";
    }
}
