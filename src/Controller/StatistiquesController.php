<?php

namespace App\Controller;
use App\Entity\Visite;
use App\Form\VisiteType;
use App\Form\VisiteFrontType;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class StatistiquesController extends AbstractController
{
    /**
     * @Route("/statistiques", name="statistiques")
     */
    public function index(): Response
    {


        $Entreprises=$this->getDoctrine()->getRepository(Visite::class)->findBy(['sexe' =>'Femme']);
        $EntreprisesNbr=sizeof($Entreprises);


        $Etudiants=$this->getDoctrine()->getRepository(Visite::class)->findBy(['sexe' => 'Homme']);
        $EtudiantsNbr=sizeof($Etudiants);



        return $this->render('statistiques/index.html.twig', [
            'controller_name' => 'StatistiquesController',
            'EntreprisesNbr'=>$EntreprisesNbr,'EtudiantsNbr'=>$EtudiantsNbr
        ]);
    }
}
