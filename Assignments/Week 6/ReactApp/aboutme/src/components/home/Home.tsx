import './Home.css';

function Home(){
    return (
        <main>
        {/* <h1>About Morgan McGrath</h1> */}
        <div id="life-story" className="LifeStory">
        <h2>Life Story</h2>
        </div>
        <div id="Early Life" className="segment">
            <h4>Early Life</h4>
            <p>
                I was born in Juneau, Alaska on April 3 1996. I don't remember much about this time, clearly, but its safe to say this was one of the coldest periods of my life. 
                My father joined the Air Force and we began to move around. I learned how to pack up quickly, make new friends, and adapt to where ever we ended up. 
                This was a bit hard to get used to, but now it feels weird to stay in one place too long. We moved across the states and ended up in Italy for a short
                time before coming back to America. 
            </p>
        </div>
        <br/>
        <div id="adolescence" className="segment">
            <h4>Adolescence</h4>
            <p>
                After Italy, we moved to South Carolina. It was a mixed time for me, got in some fights in middle school and discovered a love for 
                public speaking through some after school programs. At this point, I wanted to learn how to play the drums and joined the school band. My parents hired 
                a tutor for me, but the tutor refused to teach me how to use a drumset, instead he focused on orchestral drums. I lost interest quickly and the lessons stopped.
                I started taking martial arts classes and made some good friends through it, even won a few competitions. 
                <br/><br/>
                For high school, my family was moved to Germany. I fell in love with the cloudy skies and deep forests, even picked up a bit of German! Unfortunately, my
                ability to retain languages isn't the best so most of it has been forgotten due to lack of use. I got used to the slow moving life of the villages and would
                spend nearly every afternoon riding my bike around the countryside. As soon as I became old enough to drink, I did what most people who suddenly come of age did:
                I drank in excess. I'm glad I got that out of the way back then, when the worst that could happen (and did) was me buying a train ticket and 
                ending up in a village an hour walk away from my home. I came back to the States after I graduated and regretted it pretty much instantly.
            </p>
        </div>
        <br/>
        <div id="college-life-and-beyond" className="segment">
            <h4>College Life and Beyond</h4>
            <p>
                I went to Texas State University in San Marcos, Texas for college, with a major in Microbiology and a minor in Biochemistry. Unfortunately, thanks to a lack of focus and a dash of
                terrible advise from the advisor, I quickly found myself overwhelmed with near constant labs and science classes.
                After a while, I switched my major to something much more manageable, Criminal Justice. Don't worry, I was planning to use this to become an attorney. I picture 
                myself as a brilliant prosecutor helping ensure criminals go behind bars. Not to get too political, but that Criminal Justice classes I took quickly changed my mind and
                I decided to become a Defense Attorney instead. I met a woman and shortly after my son was born. I ended up dropping out of school to 
                work full time to support them so she could graduate. The plan was for me to go back to school after she graduated, but 
                I never felt like I really belonged in a school setting, so I ended up continuing to work. I went from minimum wage
                job to minimum wage job, each one getting slightly better than the last. The worst job I worked at was at a Little Caeser's and the best was a brief stint working for the government
                before I got hired at Revature. My partner and I split up and share custody of our son, which has been understandably difficult for him,
                and do our best to maintain a good relationship so things aren't any rougher on him.
            </p>
            </div>
            
            </main>
    );
}

export default Home;