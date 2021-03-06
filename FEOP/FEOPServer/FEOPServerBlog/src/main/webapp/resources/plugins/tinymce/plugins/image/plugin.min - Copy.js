tinymce.PluginManager.add("image", function (e) {
    function t(e, t) {
        function n(e, n) {
            i.parentNode.removeChild(i), t({width: e, height: n})
        }

        var i = document.createElement("img");
        i.onload = function () {
            n(i.clientWidth, i.clientHeight)
        }, i.onerror = function () {
            n()
        }, i.src = e;
        var a = i.style;
        a.visibility = "hidden", a.position = "fixed", a.bottom = a.left = 0, a.width = a.height = "auto", document.body.appendChild(i)
    }

    function n(t) {
        return function () {
            var n = e.settings.image_list;
            "string" == typeof n ? tinymce.util.XHR.send({url: n, success: function (e) {
                t(tinymce.util.JSON.parse(e))
            }}) : t(n)
        }
    }

    function i(n) {
        function i() {
            var e = [
                {text: "None", value: ""}
            ];
            return tinymce.each(n, function (t) {
                e.push({text: t.text || t.title, value: t.value || t.url, menu: t.menu})
            }), e
        }

        function a(e) {
            var t, n, i, a;
            t = c.find("#width")[0], n = c.find("#height")[0], i = t.value(), a = n.value(), c.find("#constrain")[0].checked() && u && m && i && a && (e.control == t ? (a = Math.round(i / u * a), n.value(a)) : (i = Math.round(a / m * i), t.value(i))), u = i, m = a
        }

        function r() {
            function t(t) {
                function i() {
                    t.onload = t.onerror = null, e.selection.select(t), e.nodeChanged()
                }

                t.onload = function () {
                    n.width || n.height || f.setAttribs(t, {width: t.clientWidth, height: t.clientHeight}), i()
                }, t.onerror = i
            }

            var n = c.toJSON();
            "" === n.width && (n.width = null), "" === n.height && (n.height = null), "" === n.style && (n.style = null), n = {src: n.src, alt: n.alt, width: n.width, height: n.height, style: n.style}, h ? f.setAttribs(h, n) : (n.id = "__mcenew", e.insertContent(f.createHTML("img", n)), h = f.get("__mcenew"), f.setAttrib(h, "id", null)), t(h)
        }

        function o(e) {
            return e && (e = e.replace(/px$/, "")), e
        }

        function l() {
            t(this.value(), function (e) {
                e.width && e.height && (u = e.width, m = e.height, c.find("#width").value(u), c.find("#height").value(m))
            })
        }

        function s() {
            function e(e) {
                return e.length > 0 && /^[0-9]+$/.test(e) && (e += "px"), e
            }

            var t = c.toJSON(), n = f.parseStyle(t.style);
            delete n.margin, n["margin-top"] = n["margin-bottom"] = e(t.vspace), n["margin-left"] = n["margin-right"] = e(t.hspace), n["border-width"] = e(t.border), c.find("#style").value(f.serializeStyle(f.parseStyle(f.serializeStyle(n))))
        }

        var c, d, u, m, g, f = e.dom, h = e.selection.getNode();
        u = f.getAttrib(h, "width"), m = f.getAttrib(h, "height"), "IMG" != h.nodeName || h.getAttribute("data-mce-object") ? h = null : d = {src: f.getAttrib(h, "src"), alt: f.getAttrib(h, "alt"), width: u, height: m}, n && (g = {name: "target", type: "listbox", label: "Image list", values: i(), onselect: function (e) {
            var t = c.find("#alt");
            (!t.value() || e.lastControl && t.value() == e.lastControl.text()) && t.value(e.control.text()), c.find("#src").value(e.control.value())
        }});
        var p = [
            {name: "src", type: "filepicker", filetype: "image", label: "Source", autofocus: !0, onchange: l},
            g,
            {name: "alt", type: "textbox", label: "Image description"},
            {type: "container", label: "Dimensions", layout: "flex", direction: "row", align: "center", spacing: 5, items: [
                {name: "width", type: "textbox", maxLength: 3, size: 3, onchange: a},
                {type: "label", text: "x"},
                {name: "height", type: "textbox", maxLength: 3, size: 3, onchange: a},
                {name: "constrain", type: "checkbox", checked: !0, text: "Constrain proportions"}
            ]}
        ];
        e.settings.image_advtab ? (h && (d.hspace = o(h.style.marginLeft || h.style.marginRight), d.vspace = o(h.style.marginTop || h.style.marginBottom), d.border = o(h.style.borderWidth), d.style = e.dom.serializeStyle(e.dom.parseStyle(e.dom.getAttrib(h, "style")))), c = e.windowManager.open({title: "Insert/edit image", data: d, bodyType: "tabpanel", body: [
            {title: "General", type: "form", items: p},
            {title: "Advanced", type: "form", pack: "start", items: [
                {label: "Style", name: "style", type: "textbox"},
                {type: "form", layout: "grid", packV: "start", columns: 2, padding: 0, alignH: ["left", "right"], defaults: {type: "textbox", maxWidth: 50, onchange: s}, items: [
                    {label: "Vertical space", name: "vspace"},
                    {label: "Horizontal space", name: "hspace"},
                    {label: "Border", name: "border"}
                ]}
            ]}
        ], onSubmit: r})) : c = e.windowManager.open({title: "Edit image", data: d, body: p, onSubmit: r})
    }

    e.addButton("image", {icon: "image", tooltip: "Insert/edit image", onclick: n(i), stateSelector: "img:not([data-mce-object])"}), e.addMenuItem("image", {icon: "image", text: "Insert image", onclick: n(i), context: "insert", prependToContext: !0})
});