package aktie.net;

import java.util.logging.Logger;

import aktie.GenericProcessor;
import aktie.data.CObj;

public class InGlbSeqProcessor extends GenericProcessor
{

    Logger log = Logger.getLogger ( "aktie" );

    private ConnectionThread conThread;

    public InGlbSeqProcessor ( ConnectionThread ct )
    {
        conThread = ct;
    }

    @Override
    public boolean process ( CObj b )
    {
        if ( CObj.SEQCOMP.equals ( b.getType() ) )
        {
            Long psq = b.getNumber ( CObj.SEQNUM );
            Long msq = b.getNumber ( CObj.MEMSEQNUM );
            Long ssq = b.getNumber ( CObj.SUBSEQNUM );

            log.info ( "GLB SEQ COMPLETE: ME: " + conThread.getLocalDestination().getIdentity().getId() +
                       " FROM: " + conThread.getEndDestination().getId() + " SEQ: " +
                       psq + " " + msq + " " + ssq );

            boolean pb = ( psq != null );
            long ps = 0;

            if ( pb ) { ps = psq; }

            boolean mb = ( msq != null );
            long ms = 0;

            if ( mb ) { ms = msq; }

            boolean sb = ( ssq != null );
            long ss = 0;

            if ( sb ) { ss = ssq; }

            conThread.setLastSeq ( pb, ps, mb, ms, sb, ss );

            return true;
        }

        return false;
    }

}
