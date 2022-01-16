/* This file was generated by SableCC (http://www.sablecc.org/). */

package minipython.node;

import java.util.*;
import minipython.analysis.*;

public final class AModule extends PModule
{
    private final LinkedList _moduleCaller_ = new TypedLinkedList(new ModuleCaller_Cast());
    private TId _id_;

    public AModule()
    {
    }

    public AModule(
        List _moduleCaller_,
        TId _id_)
    {
        {
            this._moduleCaller_.clear();
            this._moduleCaller_.addAll(_moduleCaller_);
        }

        setId(_id_);

    }
    public Object clone()
    {
        return new AModule(
            cloneList(_moduleCaller_),
            (TId) cloneNode(_id_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAModule(this);
    }

    public LinkedList getModuleCaller()
    {
        return _moduleCaller_;
    }

    public void setModuleCaller(List list)
    {
        _moduleCaller_.clear();
        _moduleCaller_.addAll(list);
    }

    public TId getId()
    {
        return _id_;
    }

    public void setId(TId node)
    {
        if(_id_ != null)
        {
            _id_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _id_ = node;
    }

    public String toString()
    {
        return ""
            + toString(_moduleCaller_)
            + toString(_id_);
    }

    void removeChild(Node child)
    {
        if(_moduleCaller_.remove(child))
        {
            return;
        }

        if(_id_ == child)
        {
            _id_ = null;
            return;
        }

    }

    void replaceChild(Node oldChild, Node newChild)
    {
        for(ListIterator i = _moduleCaller_.listIterator(); i.hasNext();)
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

        if(_id_ == oldChild)
        {
            setId((TId) newChild);
            return;
        }

    }

    private class ModuleCaller_Cast implements Cast
    {
        public Object cast(Object o)
        {
            PModuleCaller node = (PModuleCaller) o;

            if((node.parent() != null) &&
                (node.parent() != AModule.this))
            {
                node.parent().removeChild(node);
            }

            if((node.parent() == null) ||
                (node.parent() != AModule.this))
            {
                node.parent(AModule.this);
            }

            return node;
        }
    }
}
