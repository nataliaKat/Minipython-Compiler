/* This file was generated by SableCC (http://www.sablecc.org/). */

package minipython.node;

import java.util.*;
import minipython.analysis.*;

public final class ANotFirstImportModule extends PNotFirstImportModule
{
    private PModule _module_;
    private final LinkedList _importAlias_ = new TypedLinkedList(new ImportAlias_Cast());

    public ANotFirstImportModule()
    {
    }

    public ANotFirstImportModule(
        PModule _module_,
        List _importAlias_)
    {
        setModule(_module_);

        {
            this._importAlias_.clear();
            this._importAlias_.addAll(_importAlias_);
        }

    }
    public Object clone()
    {
        return new ANotFirstImportModule(
            (PModule) cloneNode(_module_),
            cloneList(_importAlias_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseANotFirstImportModule(this);
    }

    public PModule getModule()
    {
        return _module_;
    }

    public void setModule(PModule node)
    {
        if(_module_ != null)
        {
            _module_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _module_ = node;
    }

    public LinkedList getImportAlias()
    {
        return _importAlias_;
    }

    public void setImportAlias(List list)
    {
        _importAlias_.clear();
        _importAlias_.addAll(list);
    }

    public String toString()
    {
        return ""
            + toString(_module_)
            + toString(_importAlias_);
    }

    void removeChild(Node child)
    {
        if(_module_ == child)
        {
            _module_ = null;
            return;
        }

        if(_importAlias_.remove(child))
        {
            return;
        }

    }

    void replaceChild(Node oldChild, Node newChild)
    {
        if(_module_ == oldChild)
        {
            setModule((PModule) newChild);
            return;
        }

        for(ListIterator i = _importAlias_.listIterator(); i.hasNext();)
        {
            if(i.next() == oldChild)
            {
                if(newChild != null)
                {
                    i.set(newChild);
                    oldChild.parent(null);
                    return;
                }

                i.remove();
                oldChild.parent(null);
                return;
            }
        }

    }

    private class ImportAlias_Cast implements Cast
    {
        public Object cast(Object o)
        {
            PImportAlias node = (PImportAlias) o;

            if((node.parent() != null) &&
                (node.parent() != ANotFirstImportModule.this))
            {
                node.parent().removeChild(node);
            }

            if((node.parent() == null) ||
                (node.parent() != ANotFirstImportModule.this))
            {
                node.parent(ANotFirstImportModule.this);
            }

            return node;
        }
    }
}
