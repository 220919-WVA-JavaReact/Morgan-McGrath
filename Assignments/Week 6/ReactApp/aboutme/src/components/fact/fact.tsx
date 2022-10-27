import React from "react";

interface IFactProps{
    id: number,
    fact: string,
    isTrue: string
};

class Fact extends React.Component<IFactProps>{
    render() {
        return (
            <div id="option" className={this.props.isTrue}>
                    <h5>{this.props.id}</h5>
                    <p>{this.props.fact}</p>
            </div>

        );
    }
}

export default Fact;