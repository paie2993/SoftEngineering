import ReactDOM from 'react-dom'
import { createRoot } from 'react-dom/client'
import { useState } from 'react'
import { TopicsPage } from './Topics.js'
import { PaperDetailsPage } from './SelectedPaper.js'
import { PapersPage } from './Papers.js'
import { VerticalMenu } from './VerticalMenu.js'
import styles from './index.module.css'

function App() {

    const [ content, setContent ] = useState("allPapers");
    const [ paperId, setPaperId ] = useState(0);
    const userId = 1

    return (
        <div className={styles.containerDiv}>
            <VerticalMenu setContent={setContent} />
            {content === "allPapers" && 
            <PapersPage 
            setContent={setContent} 
            paperId={paperId} 
            setPaperId={setPaperId} />}
            {content === "topics" && <TopicsPage userId={userId} />}
            {content === "selectedPage" && <PaperDetailsPage paperId={paperId} />}
        </div>
    );
}

const container = document.getElementById('root');
const root = createRoot(container);
root.render(<App />);
// ReactDOM.render(<App />, document.getElementById('root'));