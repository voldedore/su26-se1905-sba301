import {Button, Container, Table} from "react-bootstrap";

function ArtistTableRow({key, artist}) {
    return (
        <tr key={key}>
            <td>{artist.id}</td>
            <td>{artist.name}</td>
            <td><Button size="sm">Edit</Button><Button variant="danger" size="sm">Delete</Button></td>
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
                list.map(ar => <ArtistTableRow key={ar.id} artist={ar} />) :
                    <tr><td colSpan={3}>There is no artists. Please add a new one bro!</td></tr>
            }
            </tbody>
        </Table>
    );
}

export function ArtistList() {
    return (
        <Container>
            <h1>Artist list</h1>
            <ArtistTable list={[]} />
        </Container>
    );
}