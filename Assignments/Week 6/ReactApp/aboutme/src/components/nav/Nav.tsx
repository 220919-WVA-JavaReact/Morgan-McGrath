import './Nav.css';
import { Link } from 'react-router-dom';



function Nav(){
    return (
        <nav>
            <ul>
                <li className="About"><Link to="">About Me</Link></li>
                <li className="TTAAL"><a href="./ttaal">Two Truths</a></li>
            </ul>
        </nav>
    );
};

export default Nav;