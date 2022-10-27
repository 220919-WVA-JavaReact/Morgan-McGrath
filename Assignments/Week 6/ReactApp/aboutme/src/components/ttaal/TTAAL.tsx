import Fact from '../fact/fact';
import './TTAAL.css';

function TTAAL(){

    const facts = [
        {
            id: 1,
            fact: 'I\'ve fallen off multiple roofs',
            isTrue: 'Truth'
        },
        {
            id: 2,
            fact: 'I got in trouble in school for running a competing store',
            isTrue: 'Lie'

        },
        {
            id: 3,
            fact: 'I\'ve sharked people with the game Cornhole',
            isTrue: 'Truth'

        },
    ]

    return (
        <main>
            <div id="two-truths-and-a-lie" className="segment">
                <h4>Two Truths and a Lie</h4>
                <p>Guess which one is the lie! Hover for the answer</p>
                <div className="Container">
                    <div id="option" className="truth">
                    <Fact id={facts[0].id}  fact={facts[0].fact} isTrue={facts[0].isTrue}/>
                    </div>
                    <div id="option" className="lie">
                    <Fact id={facts[1].id}  fact={facts[1].fact} isTrue={facts[1].isTrue}/>
                    </div>
                    <div id="option" className="truth">
                    <Fact id={facts[2].id}  fact={facts[2].fact} isTrue={facts[2].isTrue}/>
                    </div>
                    {/* {
                        facts.map(fact => (
                            <Fact id={fact.id}  fact={fact.fact} isTrue={fact.isTrue}/>
                            ))
                    } */}
                    
            
                </div>
            </div>
        </main>
    );
};

export default TTAAL;