import {Button, Container, Table} from "react-bootstrap";
import {useEffect, useState} from "react";
import {Link} from "react-router-dom";

function ArtistTableRow({key, artist}) {
    return (
        <tr key={key}>
            <td>{artist.id}</td>
            <td>{artist.name}</td>
            <td> {// String interpolate `
                 }
                <Button size="sm" as={Link} to={`/chinh-sua-nghe-si/${artist.id}`}>Edit</Button>
                <Button variant="danger" size="sm">Delete</Button>
            </td>
        </tr>
    );
}

// Props -> Data được truyền từ parent vào Component
function ArtistTable({list}) {
    return (
        <Table>
            <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            {
                list.length > 0 ?
                    list.map(ar => <ArtistTableRow key={ar.id} artist={ar}/>) :
                    <tr>
                        <td colSpan={3}>There is no artists. Please add a new one bro!</td>
                    </tr>
            }
            </tbody>
        </Table>
    );
}

// Array of Objects
const artistList = [
    {
        id: 1,
        name: 'AC/DC'
    },
    {
        id: 2,
        name: 'Dan Nguyen'
    },
    {
        id: 3,
        name: 'Bang Kieu'
    },
    {
        id: 4,
        name: 'Dan Truong'
    },
];

export function ArtistList() {
    // state artists
    const [artists, setArtists] = useState([]);

    // Call API -> get artist list
    // Inline arrow fn
    useEffect(() => {
        // code
        fetch('http://localhost:8080/api/v1/artists')
            .then(res => {
                return res.json()
            })
            .then(data => {
                setArtists(data.content)
            })
        // code
    }, []);

    return (
        <Container>
            <h1>Artist list</h1>
            <ArtistTable list={artists}/>
        </Container>
    );
}