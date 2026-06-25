let contacts = [];

(() => {
    document.querySelectorAll("input").forEach((input) => {
        input.addEventListener("change", (e) => {
            const elm = e.target;
            const id = elm.id;
            const val = elm.value;
            const length = val.length;

            const parent = elm.parentElement;

            const errElm = document.createElement("span");
            errElm.classList.add("text-danger");
            errElm.id = `err-${id}`;

            const lastErr = document.getElementById(errElm.id);
            if (lastErr) {
                lastErr.remove()
            }

            try {
                if (id == "name") {
                    const parts = val.split(" ").length;
                    if (parts < 2) {
                        throw new Error("Full name must be at least 2 parts")
                    }
                } else if (id == "tel") {
                    if (length != 10) {
                        throw new Error("Invalid phone number");
                    }
                } else if (id == "address") {
                    if (length < 5) {
                        throw new Error("Address must be at least 5 chars");
                    }
                } else {
                    const now = new Date();
                    const dateVal = new Date(val);
                    if (dateVal > now) {
                        throw new Error("DOB must be in the past");
                    }
                }
            } catch (err) {
                errElm.textContent = err.message;
                parent.appendChild(errElm);
                console.log(err.message)
            }
        });
            
    })

    // pseudo code

    function handleFormSubmit(e) {
        // Ngăn hành vi mặc định của form.
        e.preventDefault();
        // Lấy giá trị từ các trường input.
        const name = document.getElementById("name").value;
        const tel = document.getElementById("tel").value;
        const address = document.getElementById("address").value;
        const dob = document.getElementById("dob").value;

        // Tao một đối tượng contact mới với các giá trị từ form.
        const contact = {
            name: name,
            tel: tel,
            address: address,
            dob: dob
        };

        // Push contact mới vào mảng contacts.
        contacts.push(contact);

        // renderTable()
    }
    
    form.addEventListener("submit", handleFormSubmit);

    function renderTable() {
        // psedo cdoe
        // tableBody = doc.getElementById(idBang)
        // rows = [];
        // for contact in contacts:
            // row = new Element(tr)
            // row.innerHtml = td.....
            // rows.push(row)
        // tableBody.append(rows)
    }
})();



// IIFE - Immediately Invoked Function Expression
// (// fn ...) ();
