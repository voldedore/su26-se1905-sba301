import {Container, InputGroup, Form, Button, Row, Col} from "react-bootstrap";
import {FavoriteGenre} from "../genres/FavoriteGenre.jsx";
import {useState} from "react";
import {useNavigate} from "react-router-dom";

export function ArtistForm() {
    const [name, setName] = useState('');
    // Hook navigate de di chuyen giua cac route
    const navigate = useNavigate();
    // API de create artist -> Check lai ben BE
    // NGhien cuu: disabled button tranh submit nhieu lan
    const [submitting, setSubmitting] = useState(false);
    //             error msg

    // Gia lap sleep (wait 2s)
    const sleep = (ms) => new Promise((evo) => setTimeout(evo, ms));

    const handleSubmit = async (e) => {
        // Bao cho browser biet chung ta ko dung hanh vi mac dinh khi submit form
        e.preventDefault();
        console.log('Form data: ', {name});
        setSubmitting(true);
        await sleep(2000);
        // fetch -> DataType la Promise<T>
        // await fetch -> return ve T
        const res = await fetch('http://localhost:8080/api/v1/artists', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({name}) // stringify -> chuyen 1 object json ve String
        });
        // Neu tra ve code 200
        if (res.status === 201) {
            // redirect ve list
            navigate('/danh-sach-nghe-si')
        }

        setSubmitting(false);
    };
    // useEffect(fn, deps);

    // fetch() ->

    // Handle form submitted
    // 2 approaches
    // 1 -> bat su kien click cua button
    // 2 -> bat su kien form on submit

    // Prevent default behavior


    return (
        <Container>
            <h1>Add new artist</h1>
            <Form onSubmit={handleSubmit}>

                <Form.Label htmlFor="basic-url">Name</Form.Label>
                <InputGroup className="mb-3">
                    <Form.Control type="text" value={name} required
                                  onChange={(e) => setName(e.target.value)}
                    />
                </InputGroup>
                <Button variant={"success"}
                        className={"me-1"}
                        type="submit"
                        disabled={submitting}
                >
                    {submitting ? 'Saving' : 'Save'}
                </Button>
                <Button variant={"secondary"} className={"me-1"}>Reset</Button>
                <Button variant={"outline-secondary"}>Back to list</Button>

            </Form>


            <Row className={'mt-3'}>
                <Col md={3}>
                    <FavoriteGenre/>
                </Col>
                <Col md={3}>
                    <FavoriteGenre/>
                </Col>
                <Col md={3}>
                    <FavoriteGenre/>
                </Col>
                <Col md={3}>
                    <FavoriteGenre/>
                </Col>
            </Row>
        </Container>

    );
}